package crm.dao;

import crm.domain.User;

/**
 * Dao interface for User class
 * @author wenqisun
 *
 */
public interface UserDao extends BaseDao<User> {

	User login(User user);

}
