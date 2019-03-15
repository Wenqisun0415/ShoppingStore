package store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import store.dao.CategoryDao;
import store.domain.Category;
import store.util.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() throws Exception{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	@Override
	public void add(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into category values (?, ?)";
		runner.update(sql, category.getCid(), category.getCname());
	}

	@Override
	public void deleteById(String cid) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from category where cid = ?";
		runner.update(sql, cid);
	}

}
