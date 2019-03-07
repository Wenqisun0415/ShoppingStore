package store.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Administrator
 *
 */
public class EncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
	
		chain.doFilter(new MyRequest(request), response);
	}

	@Override
	public void destroy() {

	}

}

class MyRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	
	@Override
	public String getParameter(String name) {  
		try {
			request.setCharacterEncoding("utf-8");
			return request.getParameter(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	/**
	 * hobby=[eat,drink]
	 */
	public String[] getParameterValues(String name) {
		try {
			request.setCharacterEncoding("utf-8");
			return request.getParameterValues(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	/**
	 * map{ username=[tom],password=[123],hobby=[eat,drink]}
	 */
	public Map<String,String[]> getParameterMap() {  
		try {
			request.setCharacterEncoding("utf-8");
			return request.getParameterMap();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}