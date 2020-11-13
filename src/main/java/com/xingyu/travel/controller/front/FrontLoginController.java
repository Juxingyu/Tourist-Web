package com.xingyu.travel.controller.front;

import com.xingyu.travel.model.*;
import com.xingyu.travel.services.*;
import com.xingyu.travel.task.mailTask;
import com.xingyu.travel.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller.front
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/front/login")
public class FrontLoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private FunsService funsService;
	@Autowired
	private NotesService notesService;
	@Autowired
	private SceneryService sceneryService;
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * @Description: 跳转前台用户登录界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/tologin")
	public String login() {
		return "/front/login/login";
	}

	/**
	 * @Description: 前台用户登录
	 * @Author: Admin
	 * @Param: [vo, request, response, session]
	 * @Return void
	 **/
	@RequestMapping("/login")
	public void login(LoginVo vo, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		//获取session中存储的验证码
		String validateCode = (String) session.getAttribute("validateCode");
		//判断验证码是否一致
		if (validateCode.toUpperCase().equals(vo.getValidateCode().toUpperCase())) {
			//验证用户名与密码是否匹配
			Users users = userService.findByLoginVo(vo);
			if (null == users) {
				//用户名密码错误
				response.getWriter().write("2");
			} else {
				//登陆成功,将用户的IP做为凭证存取到redis中
				String ip = request.getRemoteAddr();
				//查询该用户所具有的权限
				List<Funs> funsList = funsService.findFunsByUsersId(users.getUserId());
				for (Funs funs : funsList) {
					List<Funs> subList = funsService.findSubFunsByFunId(funs.getFunId());
					funs.setSubList(subList);
				}
				users.setFunsList(funsList);
				//查询攻略类型、景点类型、游记类型、
				List<Notestype> notesTypeList = notesService.findAllNoteType();
				List<Scenictype> scenicTypeList = sceneryService.findAllSceneryType();
				List<Strategytype> strategyTypeList = strategyService.findAllStrategyType();
				users.setScenicTypeList(scenicTypeList);
				users.setStrategyTypeList(strategyTypeList);
				//将作为键，users存储在redis中
				redisTemplate.boundValueOps(ip).set(users);
				//根据用户id查询roleid
				session.setAttribute("users", users);
				response.getWriter().write("3");
			}
		} else {
			//验证码错误，返回数字1
			response.getWriter().write("1");
		}
	}

	/**
	 * @Description: 跳转注册界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "/front/login/register";
	}

	/**
	 * @Description: 校验邮箱
	 * @Author: Admin
	 * @Param: [users, volidate, request]
	 * @Return void
	 **/
	@RequestMapping("/checkEmail")
	public void checkEmail(String email, HttpServletResponse response) throws IOException, MessagingException {
		Users user = userService.findByEmail(email);
		if (null == user) {//邮箱可用
			//发送邮件
			String validate = new Random().nextInt(900000) + 10000 + "";//六位随机数
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setSubject("您的账号正在注册邮箱操作！");
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("xingyu2419586726@163.com");
			helper.setTo(email);
			helper.setText("您的随机码是：" + validate + ";请前往填写（两分钟有效）");
			// 创建一个任务，传递给任务所需要的参数
			mailTask mailTask = new mailTask(mailSender, mimeMessage);
			taskExecutor.execute(mailTask);// 执行器（线程池）执行任务
			response.getWriter().write("1");//给界面响应，随机码发送成功
			redisTemplate.boundValueOps("volidate:" + email).set(validate, 120, TimeUnit.MINUTES);
		} else {
			//邮箱已经被注册
			response.getWriter().write("0");
		}
	}

	/**
	 * @Description: 注册接口
	 * @Author: Admin
	 * @Param:
	 * @Return
	 **/
	@RequestMapping("/register")
	public void register(Users users, String volidate, HttpServletResponse response) throws IOException {
		String voli = (String) redisTemplate.boundValueOps("volidate:" + users.getEmail()).get();
		//验证码正确
		if (volidate.toUpperCase().equals(voli.toUpperCase())) {
			int i = userService.insert(users);
			if (i > 0) {
				//注册成功
				response.getWriter().write("2");
			} else {
				//注册失败
				response.getWriter().write("1");
			}
		} else {
			//验证码错误
			response.getWriter().write("0");
		}
	}

	/**
	 * @Description: 跳转忘记密码
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/forgetPassword")
	public String forgetPassword() {
		return "/front/login/forgetPassword";
	}

	/**
	 * @Description: 判断用户名是否存在
	 * @Author: Admin
	 * @Param: [users, volidate, response, session]
	 * @Return void
	 **/
	@RequestMapping("/checkFrom")
	public void checkLoginNameAndVolidata(Users users, String volidate, HttpServletResponse response, HttpSession session) throws IOException {
		Users loginNameAndEmail = userService.findLoginNameAndEmail(users);
		//用户名存在
		if (null != loginNameAndEmail) {
			//取验证码
			String validateCode = (String) session.getAttribute("validateCode");
			//	校验验证码
			if (validateCode.toUpperCase().equals(volidate.toUpperCase())) {
				//校验通过
				response.getWriter().write("3");
			} else {
				//验证码错误
				response.getWriter().write("2");
			}
		} else {
			//用户信息有误
			response.getWriter().write("1");
		}
	}

	/**
	 * @Description: 获取新密码
	 * @Author: Admin
	 * @Param: [email, response]
	 * @Return void
	 **/
	@RequestMapping("/sendEmail")
	public void sendEmail(String email, HttpServletResponse response) throws IOException, MessagingException {
		Users user = userService.findByEmail(email);
		//发送邮件
		String validate = new Random().nextInt(900000) + 10000 + "";//六位随机数
		boolean b = userService.updatePass(user.getUserId(), validate);
		if (b) {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setSubject("您的账号正在使用密码找回！");
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("xingyu2419586726@163.com");
			helper.setTo(email);
			helper.setText("您的随机码是：" + validate + ";请前往填写（两分钟有效），请及时修改密码");
			// 创建一个任务，传递给任务所需要的参数
			mailTask mailTask = new mailTask(mailSender, mimeMessage);
			taskExecutor.execute(mailTask);// 执行器（线程池）执行任务
			response.getWriter().write("1");//给界面响应，随机码发送成功
			redisTemplate.boundValueOps("volidate:" + email).set(validate, 120, TimeUnit.MINUTES);
		} else {
			response.getWriter().write("2");
		}
	}

	/**
	 * 退出登录
	 **/
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// session失效
		session.invalidate();
		return "redirect:/front/login/tologin";
	}
}
