package store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.domain.Product;
import store.service.ProductService;
import store.service.impl.ProductServiceImpl;
import store.web.base.BaseServlet;

public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ProductService service = new ProductServiceImpl();
		List<Product> hotPro = service.findHot();
		List<Product> newPro = service.findNew();
		req.setAttribute("hotPro", hotPro);
		req.setAttribute("newPro", newPro);
		
		return "/jsp/index.jsp";
	}

}
