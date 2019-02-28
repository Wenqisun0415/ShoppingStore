package crm.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.domain.User;
import crm.service.UserService;

/**
 * Action class for User
 * @author wenqisun
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private User user = new User();
	//injection of UserService
	private UserService userService;

	@Override
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	/**
	 * register method for user
	 * @return
	 */
	public String register() {
		userService.register(user);
		return LOGIN;
	}

	public String login() {
		//find user in database
		User existUser = userService.login(user);
		if(existUser == null) {
			//login failed
			this.addActionError("用户名或密码错误");
			return LOGIN;
		} else {
			//login succeed
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS;
		}
	}
}
