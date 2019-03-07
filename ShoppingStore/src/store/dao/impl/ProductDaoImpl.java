package store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import store.dao.ProductDao;
import store.domain.Product;
import store.util.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findHot() throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pflag=0 and is_hot=1 order by pdate desc limit 9";
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findNew() throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pflag=0 order by pdate desc limit 9";
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pid=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class), pid);
		return product;
	}

	@Override
	public List<Product> findProductByCidWithPage(String cid, int pageNum, int pageSize) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where cid=? limit ? offset ?";
		int offset = (pageNum - 1) * pageSize;
		Object[] params = {cid, pageSize, offset};
		return runner.query(sql, new BeanListHandler<Product>(Product.class), params);
	}

	@Override
	public int findProductNumByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Long num = (Long) runner.query(sql, new ScalarHandler(), cid);
		return num.intValue();
	}

}
