package store.dao;

import java.util.List;

import store.domain.Order;
import store.domain.OrderItem;
import store.domain.User;

public interface OrderDao {

	public void saveOrder(Order order) throws Exception;
	
	public void saveOrderItem(OrderItem orderItem) throws Exception;

	public List<Order> findOdersByUserWithPage(User user, int pageNum, int pageSize) throws Exception;

	public int findOrderNumByUser(User user) throws Exception;

	public Order findOrderByOid(String oid) throws Exception;

	public void updateOrder(Order order) throws Exception;
	
}
