package store.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		String method = req.getParameter("method");
		
		if(method == null || method.trim().equals("")) {
			method = "execute";
		}
		
		try {
			Class<? extends BaseServlet> clazz = this.getClass();
			Method md = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			if(md != null) {
				String jspPath = (String) md.invoke(this, req, resp);
				if(jspPath != null) {
					req.getRequestDispatcher(jspPath).forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return null;
	}

}
