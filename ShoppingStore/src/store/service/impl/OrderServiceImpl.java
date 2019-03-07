package store.service.impl;

import java.util.List;

import store.dao.OrderDao;
import store.dao.impl.OrderDaoImpl;
import store.domain.Order;
import store.domain.OrderItem;
import store.domain.PageBean;
import store.domain.User;
import store.service.OrderService;
import store.util.JDBCUtils;

public class OrderServiceImpl implements OrderService {
	
	private int PAGE_SIZE = 5;
	
	private OrderDao dao = new OrderDaoImpl();

	@Override
	public void saveOrder(Order order) throws Exception {
		try {
			JDBCUtils.startTransaction();
			dao.saveOrder(order);
			List<OrderItem> orderItems = order.getOrderItems();
			for (OrderItem orderItem : orderItems) {
				dao.saveOrderItem(orderItem);
			}
			JDBCUtils.commitAndClose();
		} catch (Exception e) {
			JDBCUtils.rollbackAndClose();
			e.printStackTrace();
		}
	}

	@Override
	public PageBean<Order> findOrdersByUser(User user, int pageNum) throws Exception{
		List<Order> orders = dao.findOdersByUserWithPage(user, pageNum, PAGE_SIZE);
		int totalRecords = dao.findOrderNumByUser(user);
		PageBean<Order> page = new PageBean<Order>(pageNum, PAGE_SIZE, totalRecords);
		page.setList(orders);
		String url = "OrderServlet?method=showOrders";
		page.setUrl(url);
		return page;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		Order order = dao.findOrderByOid(oid);
		return order;
	}

	@Override
	public void updateOrder(Order order) throws Exception{
		dao.updateOrder(order);
	}

}
