package store.service;

import store.domain.Order;
import store.domain.PageBean;
import store.domain.User;

public interface OrderService {

	public void saveOrder(Order order) throws Exception;
	
	public PageBean<Order> findOrdersByUser(User user, int pageNum) throws Exception;
	
	public Order findOrderByOid(String oid) throws Exception;

	public void updateOrder(Order order) throws Exception;
	
}
