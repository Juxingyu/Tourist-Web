package com.xingyu.travel.security;

import com.xingyu.travel.model.Funs;
import com.xingyu.travel.model.Users;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class SessionPermissionInterceptor implements HandlerInterceptor {
	/**
	 * @Description: 拦截器    请求来时，拦截此次请求，判断其 权限等问题----------------去spring-mvc配置拦截器
	 * <p>
	 * 拦截后---判断路径中是否包含login，（判断是否为公开资源）
	 * 是---放行，reture true;
	 * 否---拦截，从redis中获取session httpsession的getsession(boolean create) fasle 不存在session不创建;
	 * null=session		未登录（去登录）
	 * 否则	-----去session中取user凭证
	 * 有-----放行
	 * 无-----判断权限
	 * 有权限---放行
	 * 无权限---非法访问
	 **/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("进入拦截器");
		String path = request.getRequestURI();
		if (path.contains("login")) {
			return true;
		} else {
			HttpSession session = request.getSession(false);
			if (null == session) {
				//	未登录
				response.sendRedirect(request.getContextPath() + "/back/login/tologin");
				return false;
			} else {
				Users user = (Users) session.getAttribute("user");
				if (null == user) {
					//	未登录
					response.sendRedirect(request.getContextPath() + "/back/login/tologin");
					return false;
				} else {
					//	判断是否拥有该权限
					List<Funs> funsList = user.getFunsList();
					boolean check = false;
					for (Funs funs : funsList) {
						if (path.contains(funs.getFunKey())) {
							check = true;
							break;
						}
					}
					if (check) {
						return true;
					} else {
						//	非法访问
						response.sendRedirect(request.getContextPath() + "/login/noauth");
						return false;
					}
				}
			}
		}
	}

	// /**
	//  * @Description: 拦截器    在返回视图之前 设置ctx 代表当前路径		去spring-mvc配置拦截器
	//  **/
	// @Override
	// public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
	// 	request.setAttribute("ctx", request.getContextPath());
	// }

	public boolean isStatic(StringBuffer url) {
		boolean result = false;
		String[] arr = { //定义一个需要放行的数组
				"/login",
				"/css",
				"/images",//图片
				"/js" //js脚本
		};
		for (String a : arr) {
			if (url.indexOf(a) != -1) {
				result = true;
			}
		}
		return result;
	}
}
