package store.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.domain.User;
import store.service.UserService;
import store.service.impl.UserServiceImpl;
import store.util.MailUtils;
import store.util.MyBeanUtils;
import store.util.UUIDUtils;
import store.web.base.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public String registerUI(HttpServletRequest req, HttpServletResponse resp) {
		return "/jsp/register.jsp";
	}
	
	public String loginUI(HttpServletRequest req, HttpServletResponse resp) {
		return "/jsp/login.jsp";
	}
	
	public String register(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		User user = new User();
		user = MyBeanUtils.populate(user.getClass(), parameterMap);

		user.setUid(UUIDUtils.getId());
		user.setCode(UUIDUtils.getCode());
		UserService service = new UserServiceImpl();
		try {
			service.registerUser(user);
			MailUtils.sendMail(user.getEmail(), user.getCode());
			System.out.println("邮件已发送");
			req.setAttribute("msg", "注册成功，请查看邮箱激活");
		} catch (Exception e) {
			req.setAttribute("msg", "注册失败，请重新注册");
			e.printStackTrace();
		}
		
		return "/jsp/info.jsp";
	}
	
	public String activate(HttpServletRequest req, HttpServletResponse resp) {
		String code = req.getParameter("code");
		UserService service = new UserServiceImpl();
		try {
			boolean isActivated = service.activateUser(code);
			if(isActivated) {
				req.setAttribute("msg", "激活成功，请登录");
			} else {
				req.setAttribute("msg", "激活失败，请重新注册");
			}
			return "/jsp/info.jsp";
		} catch (Exception e) {
			req.setAttribute("msg", "激活失败，请重新注册");
			e.printStackTrace();
			return "/jsp/info.jsp";
		}
	}
	
	public String login(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserService service = new UserServiceImpl();
		try {
			User user = service.loginUser(username, password);
			if(user != null) {
				if(user.getState() == 0) {
					req.setAttribute("msg", "用户未激活");
					return "/jsp/login.jsp";
				} else {
					req.getSession().setAttribute("UserBean", user);
					resp.sendRedirect("/ShoppingStore/index.jsp");
					return null;
				}
			} else {
				req.setAttribute("msg", "账号或密码错误");
				return "/jsp/login.jsp";
			}
		} catch (Exception e) {
			req.setAttribute("msg", "登录失败");
			e.printStackTrace();
			return "/jsp/info.jsp";
		}
	}

	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		try {
			resp.sendRedirect("/ShoppingStore/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
