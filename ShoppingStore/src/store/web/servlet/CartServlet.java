package store.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.domain.Cart;
import store.domain.CartItem;
import store.domain.Product;
import store.service.ProductService;
import store.service.impl.ProductServiceImpl;
import store.web.base.BaseServlet;

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String showCart(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.sendRedirect("/BookStore/jsp/cart.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
       
	public String addItem(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String pid = req.getParameter("pid");
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			ProductService service = new ProductServiceImpl();
			Product product = service.findProductByPid(pid);
			CartItem cartItem = new CartItem(product, quantity);
			Cart cart = (Cart) req.getSession().getAttribute("cart");
			if(cart == null) {
				cart = new Cart();
			}
			cart.addCartItem(cartItem);
			req.getSession().setAttribute("cart", cart);
			resp.sendRedirect("/BookStore/jsp/cart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteItem(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String pid = req.getParameter("pid");
			Cart cart = (Cart) req.getSession().getAttribute("cart");
			cart.removeCartItem(pid);
			resp.sendRedirect("/BookStore/jsp/cart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String clearCart(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Cart cart = (Cart) req.getSession().getAttribute("cart");
			cart.clearCart();
			resp.sendRedirect("/BookStore/jsp/cart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
