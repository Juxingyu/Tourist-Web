package com.xingyu.travel.controller.front;

import com.xingyu.travel.model.Users;
import com.xingyu.travel.services.UserService;
import com.xingyu.travel.task.mailTask;
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
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 用户相关操作
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/users")
public class FrontUsersController {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * @Description: 根据用户id查询名字
	 * @Author: Admin
	 * @Param:
	 * @Return
	 **/
	@RequestMapping("/findUsersById")
	public void findUsersById(Integer userId, HttpServletRequest request) {
		Users users = userService.findById(userId);
		request.setAttribute("users", users);
	}

	/**
	 * @Description: 根据登录用户ID查询用户个人信息（展示个人信息）
	 * @Author: Admin
	 * @Param: [user, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/usersInfo")
	public String findById(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		Users users = (Users) redisTemplate.boundValueOps(ip).get();
		request.setAttribute("users", users);
		return "/front/users/userInfo";
	}

	/**
	 * @Description: 改变用户状态（删除用户）
	 * @Author: Admin
	 * @Param: [users, response]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateUsersStatus")
	public String updateUsersStatus(Users users, HttpServletRequest request) throws IOException {
		int i = userService.updateUsersStatus(users);
		request.setAttribute("i", i);
		return "/back/admin/userList";
	}

	/**
	 * @Description: 跳转修改密码界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toUpdatePW")
	public String toUpdatePW() {
		return "/back/users/updatePW";
	}

	/**
	 * @Description: 跳转前端修改密码界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toFrontUpdatePW")
	public String toFrontUpdatePW() {
		return "/front/users/updatePW";
	}

	/**
	 * @Description: 修改密码
	 * @Author: Admin
	 * @Param: []
	 * @Return void
	 **/
	@RequestMapping("/changePwd")
	public void changePwd(String oldPwd, String newPwd, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ip = request.getRemoteAddr();
		Users users = (Users) redisTemplate.boundValueOps(ip).get();
		boolean b = userService.findByIdAndPassword(users.getUserId(), oldPwd);
		//	验证旧密码
		if (b) {
			//	修改新密码
			boolean b1 = userService.updatePass(users.getUserId(), newPwd);
			if (b1) {
				//	修改成功
				response.getWriter().write("1");
			} else {
				//	修改失败
				response.getWriter().write("2");
			}
		} else {
			//原密码错误
			response.getWriter().write("3");
		}
	}

	/**
	 * @Description: 跳转修改邮箱界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toChangeEmail")
	public String toChangeEmail() {
		return "/back/users/changeEmail";
	}

	/**
	 * @Description: 校验邮箱、发送随机码
	 **/
	@RequestMapping("/checkEmail")
	public void checkEmail(String email, HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException {
		//根据email查询是否存在用户（验证email唯一）
		Users users = userService.findByEmail(email);
		if (null == users) {
			//邮箱唯一
			Users users1 = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
			//发送邮件
			String validate = new Random().nextInt(900000) + 10000 + "";//六位随机数
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setSubject("您的账号正在变更邮箱操作！");
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("xingyu2419586726@163.com");
			helper.setTo(email);
			helper.setText("您的随机码是：" + validate + ";请前往填写（两分钟有效）");
			// 创建一个任务，传递给任务所需要的参数
			mailTask mailTask = new mailTask(mailSender, mimeMessage);
			taskExecutor.execute(mailTask);// 执行器（线程池）执行任务
			response.getWriter().write('1');//给界面响应，随机码发送成功
			redisTemplate.boundValueOps("validate" + users1.getUserId()).set(validate, 120, TimeUnit.MINUTES);
		} else {
			//邮箱已经存在
			response.getWriter().write("0");
		}
	}

	/**
	 * @Description: 校验随机码，修改邮箱
	 * @Author: Admin
	 * @Param:
	 * @Return
	 **/
	@RequestMapping("changeEmail")
	public void changeEmail(String email, String validate, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		users.setEmail(email);
		String vali = (String) redisTemplate.boundValueOps("validate" + users.getUserId()).get();
		//随机码正确
		if (vali.equals(validate)) {
			//修改邮箱
			int i = userService.updateEmail(users);
			if (i > 0) {//修改成功
				response.getWriter().write("1");
			} else {//修改失败
				response.getWriter().write("2");
			}
		} else {
			//随机码错误
			response.getWriter().write("0");
		}
	}

	/**
	 * @Description: 修改用户密码
	 * @Author: Admin
	 * @Param: [newPass, response, session]
	 * @Return void
	 **/
	@RequestMapping("/updatePW")
	public void updatePW(String newPass, HttpServletResponse response, HttpServletRequest request) throws IOException {
		String ip = request.getRemoteAddr();
		Users users = (Users) redisTemplate.boundValueOps(ip).get();
		boolean b = userService.updatePass(users.getUserId(), newPass);
		if (b) {
			response.getWriter().write("1");
		} else {
			response.getWriter().write("2");
		}

	}

	@RequestMapping("/toSetting")
	public String setting() {
		return "/back/users/setting";
	}
}
