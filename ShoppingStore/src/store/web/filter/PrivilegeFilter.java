package store.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import store.domain.User;

/**
 * Servlet Filter implementation class PrivilegeFilter
 */
public class PrivilegeFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("UserBean");
		if(user != null) {
			chain.doFilter(req, response);
		} else {
			req.setAttribute("msg", "请先登录再进行操作");
			req.getRequestDispatcher("/jsp/info.jsp").forward(req, response);
		}
	}

}
