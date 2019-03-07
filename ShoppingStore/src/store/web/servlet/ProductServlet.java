package store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.domain.PageBean;
import store.domain.Product;
import store.service.ProductService;
import store.service.impl.ProductServiceImpl;
import store.web.base.BaseServlet;

public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public String findProductByPid(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String pid = (String) req.getParameter("pid");
			ProductService service = new ProductServiceImpl();
			Product product = service.findProductByPid(pid);
			req.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/product_info.jsp";
	}
	
	public String findProductsByCat(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String cid = req.getParameter("cid");
			int pageNum = Integer.parseInt(req.getParameter("page"));
			ProductService service = new ProductServiceImpl();
			PageBean<Product> page = service.findProductsByCat(cid, pageNum);
			req.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/product_list.jsp";
	}
	
}
