package crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import crm.domain.User;

/**
 * 权限拦截器
 * @author wenqisun
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null) {
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("请登录");
			return "login";
		} else {
			return invocation.invoke();
		}
	}

}
