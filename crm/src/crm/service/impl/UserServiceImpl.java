package crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.UserDao;
import crm.domain.User;
import crm.service.UserService;
import crm.utils.MD5Utils;

/**
 * UserServiceImpl for User class
 * @author wenqisun
 *
 */
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void register(User user) {
		//encrypt the password
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		
		user.setUser_state("1");
		userDao.save(user);
	}

	@Override
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
