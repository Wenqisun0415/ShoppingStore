package crm.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.domain.Customer;
import crm.domain.PageBean;
import crm.domain.SaleVisit;
import crm.domain.User;
import crm.service.CustomerService;
import crm.service.SaleVisitService;
import crm.service.UserService;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private SaleVisit saleVisit = new SaleVisit();
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	
	private Integer currPage = 1;
	private Integer pageSize = 3;
	private Date visit_end_time;

	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	public Date getVisit_end_time() {
		return visit_end_time;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			this.pageSize = 3;
		} else {
			this.pageSize = pageSize;
		}
	}

	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			this.currPage = 1;
		} else {
			this.currPage = currPage;
		}
	}
	
	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		//设置条件
		if(saleVisit.getVisit_time() != null) {
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time != null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String addUI() {
		List<User> userList = userService.findAll();
		List<Customer> customerList = customerService.findAll();
		ActionContext.getContext().getValueStack().set("userList", userList);
		ActionContext.getContext().getValueStack().set("customerList", customerList);
		return "addUI";
	}
	
	public String save() {
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}

	public String delete() {
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
}
