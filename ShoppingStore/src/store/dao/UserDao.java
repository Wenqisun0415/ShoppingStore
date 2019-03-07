package store.dao;

import java.sql.SQLException;

import store.domain.User;

public interface UserDao {

	public void addUser(User user) throws SQLException;
	
	public User findUserByCode(String code) throws SQLException;
	
	public void updateUser(User user) throws SQLException;
	
	public User findUserByLogin(String username, String password) throws SQLException;
	
}
