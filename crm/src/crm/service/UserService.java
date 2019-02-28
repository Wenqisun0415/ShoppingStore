package crm.service;

import java.util.List;

import crm.domain.User;

/**
 * Service interface for User class
 * @author wenqisun
 *
 */
public interface UserService {

	void register(User user);

	User login(User user);

	List<User> findAll();

}
