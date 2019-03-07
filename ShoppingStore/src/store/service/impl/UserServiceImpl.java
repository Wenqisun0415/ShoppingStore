package store.service.impl;

import java.sql.SQLException;

import store.dao.UserDao;
import store.dao.impl.UserDaoImpl;
import store.domain.User;
import store.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void registerUser(User user) throws SQLException {
		UserDao dao = new UserDaoImpl();
		dao.addUser(user);
	}

	@Override
	public boolean activateUser(String code) throws SQLException {
		UserDao dao = new UserDaoImpl();
		User user = dao.findUserByCode(code);
		if(user != null) {
			user.setState(1);
			user.setCode(null);
			dao.updateUser(user);
			return true;
		}
		return false;
	}

	@Override
	public User loginUser(String username, String password) throws SQLException {
		UserDao dao = new UserDaoImpl();
		User user = dao.findUserByLogin(username, password);
		return user;
	}

}
