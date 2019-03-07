package store.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import store.dao.OrderDao;
import store.domain.Order;
import store.domain.OrderItem;
import store.domain.Product;
import store.domain.User;
import store.util.JDBCUtils;

public class OrderDaoImpl implements OrderDao {
	
	static {
		// 1_创建时间类型的转换器
		DateConverter dt = new DateConverter();
		// 2_设置转换的格式
		dt.setPattern("yyyy-MM-dd HH:mm:ss");
		// 3_注册转换器
		ConvertUtils.register(dt, java.sql.Timestamp.class);
	}

	@Override
	public void saveOrder(Order order) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into `order` values(?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(),
				order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid()};
		runner.update(sql, params);
	}
	
	public void saveOrderItem(OrderItem orderItem) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into orderitem values(?, ?, ?, ?, ?)";
		Object[] params = {orderItem.getItemid(), orderItem.getQuantity(), orderItem.getTotal()
				, orderItem.getProduct().getPid(), orderItem.getOrder().getOid()};
		runner.update(sql, params);
	}

	@Override
	public List<Order> findOdersByUserWithPage(User user, int pageNum, int pageSize) throws Exception{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		//firstly, find orders made by this user
		String sql = "select * from `order` where uid=? order by ordertime desc limit ? offset ?";
		Object[] params = {user.getUid(), pageSize, (pageNum-1)*pageSize};
		List<Order> orders = runner.query(sql, new BeanListHandler<Order>(Order.class), params);
		for (Order order : orders) {
			order.setUser(user);
			//for each order, find the information needed for orderitems
			sql = "select * from product p inner join orderitem o on p.pid=o.pid where o.oid =?";
			List<Map<String, Object>> maps = runner.query(sql, new MapListHandler(), order.getOid());
			for (Map<String, Object> map : maps) {
				OrderItem orderItem = new OrderItem();
				//fill orderItem object
				BeanUtils.populate(orderItem, map);
				Product product = new Product();
				//fill product object
				BeanUtils.populate(product, map);
				orderItem.setProduct(product);
				orderItem.setOrder(order);
				//put orderitem into order
				order.getOrderItems().add(orderItem);
			}
		}
		return orders;
	}

	@Override
	public int findOrderNumByUser(User user) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from `order` where uid=?";
		Long count = (Long) runner.query(sql, new ScalarHandler(), user.getUid());
		return count.intValue();
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from `order` where oid=?";
		Order order = runner.query(sql, new BeanHandler<Order>(Order.class), oid);
		sql = "select * from product p inner join orderitem o on p.pid=o.pid where oid=?";
		List<Map<String, Object>> list = runner.query(sql, new MapListHandler(), oid);
		for (Map<String, Object> map : list) {
			OrderItem orderItem = new OrderItem();
			BeanUtils.populate(orderItem, map);
			Product product = new Product();
			BeanUtils.populate(product, map);
			orderItem.setProduct(product);
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		return order;
	}

	@Override
	public void updateOrder(Order order) throws Exception{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update `order` set total=?, state=?, address=?, name=?, telephone=? where oid=?";
		Object[] params = {order.getTotal(), order.getState(), order.getAddress(), order.getName(),
				order.getTelephone(), order.getOid()};
		runner.update(sql, params);
	}

}
