package store.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import store.dao.UserDao;
import store.domain.User;
import store.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

	
	public void addUser(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user values(?, ?, ?, ?, ?, null, ?, ?, ?, ?)";
		runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getBirthday()
				, user.getGender(), user.getState(), user.getCode());
	}

	@Override
	public User findUserByCode(String code) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where code=?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), code);
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username=?, password=?, name=?, email=?, telephone=?, birthday=?, gender=?, "
				+ "state=?, code=? where uid=?";
		Object[] params = {user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(),
				user.getBirthday(), user.getGender(), user.getState(), user.getCode(), user.getUid()};
		runner.update(sql, params);
	}

	@Override
	public User findUserByLogin(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		Object[] params = {username, password};
		User user = runner.query(sql, new BeanHandler<User>(User.class), params);
		return user;
	}

}
