package store.service;

import java.sql.SQLException;

import store.domain.User;

public interface UserService {

	public void registerUser(User user) throws SQLException;
	
	public boolean activateUser(String code) throws SQLException;
	
	public User loginUser(String username, String password) throws SQLException;
}
