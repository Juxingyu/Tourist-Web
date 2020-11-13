package com.xingyu.travel.controller.back;

import com.xingyu.travel.model.*;
import com.xingyu.travel.services.*;
import com.xingyu.travel.task.mailTask;
import com.xingyu.travel.util.SHA1Util;
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

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/back/login")
public class LoginController {
	@Autowired
	private AdminService adminService;
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
	 * @Description: 跳转登录界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/tologin")
	public String toLogin() {
		return "back/login/login";
	}

	@RequestMapping("/login")
	public void login(LoginVo vo, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		//获取session中存储的验证码
		String validateCode = (String) session.getAttribute("validateCode");
		//判断验证码是否一致
		if (validateCode.toUpperCase().equals(vo.getValidateCode().toUpperCase())) {
			//验证用户名与密码是否匹配
			Admin admin = adminService.findByLoginVo(vo);
			if (null == admin) {
				//用户名密码错误
				response.getWriter().write("2");
			} else {
				//登陆成功,将用户的IP做为凭证存取到redis中
				String ip = request.getRemoteAddr();
				//查询该用户所具有的权限
				List<Funs> funsList = funsService.findFunsByAdminId(admin.getId());
				for (Funs funs : funsList) {
					List<Funs> subList = funsService.findSubFunsByFunId(funs.getFunId());
					funs.setSubList(subList);
				}
				admin.setFunsList(funsList);
				//查询攻略类型、景点类型、游记类型、
				List<Notestype> notesTypeList = notesService.findAllNoteType();
				List<Scenictype> scenicTypeList = sceneryService.findAllSceneryType();
				List<Strategytype> strategyTypeList = strategyService.findAllStrategyType();
				admin.setNotesTypeList(notesTypeList);
				admin.setScenicTypeList(scenicTypeList);
				admin.setStrategyTypeList(strategyTypeList);
				//将users作为键存储在redis中
				redisTemplate.boundValueOps(ip).set(admin);
				session.setAttribute("admin", admin);
				// session.setAttribute("users", admin);
				//管理员登录成功
				response.getWriter().write("3");
			}
		} else {
			//验证码错误，返回数字1
			response.getWriter().write("1");
		}
	}

	/**
	 * @Description: 忘记密码界面
	 **/
	@RequestMapping("/forgetPassword")
	public String forgetPassword() {
		return "/back/admin/forgetPassword";
	}

	/**
	 * @Description: 密码找回，发送新密码到邮箱
	 **/
	@RequestMapping("/getPassword")
	public void findLoginNameAndEmail(Admin admin, HttpServletResponse response) throws IOException, MessagingException {
		admin = adminService.findLoginNameAndEmail(admin);
		if (null == admin) {
			//用户不存在
			response.getWriter().write("1");
		} else {
			//随机获取新密码
			String pass = new Random().nextInt(900000) + 10000 + "";
			String newEcodePassword = SHA1Util.encode(SHA1Util.encode(pass.trim()));
			//提交到数据库
			adminService.updatePass(admin.getId(), newEcodePassword);
			//发送邮件
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setSubject("欢迎使用密码找回界面");
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("xingyu2419586726@163.com");
			helper.setTo(admin.getEmail());
			helper.setText("您的新密码是：" + pass + ";请您务必尽早更改密码！");
			// 创建一个任务，传递给任务所需要的参数
			mailTask mailTask = new mailTask(mailSender, mimeMessage);
			taskExecutor.execute(mailTask);// 执行器（线程池）执行任务
			response.getWriter().write('2');//给界面响应，密码发送成功
		}
	}

	/**
	 * @Description: 非法访问界面
	 **/
	@RequestMapping("/noauth")
	public String noauth() {
		return "/back/login/noauth";
	}

	/**
	 * @Description: 去注册界面
	 **/
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "/back/login/register";
	}

	/**
	 * @Description: 注册提交到数据库，重定向到登录页面
	 **/
	@RequestMapping("/register")
	public String register(Admin admin) {
		adminService.insert(admin);
		return "redirect:/back/login/tologin";
	}


	// /**
	//  * @Description:校验用户名唯一 ，不可重复注册
	//  **/
	// @RequestMapping("/checkLoginName")
	// public void checkLoginName(String loginName, HttpServletResponse response) throws IOException {
	// 	Users users = userService.findByLoginName(loginName);
	// 	if (null == users) {
	// 		response.getWriter().write("1");
	// 	} else {
	// 		response.getWriter().write("0");
	// 	}
	// }


	/**
	 * 退出登录
	 **/
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// session失效
		session.invalidate();
		return "redirect:/back/login/tologin";
	}
}
