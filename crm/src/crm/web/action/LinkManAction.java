package crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.domain.Customer;
import crm.domain.LinkMan;
import crm.domain.PageBean;
import crm.service.CustomerService;
import crm.service.LinkManService;

/**
 * Action class for LinkMan
 * @author wenqisun
 *
 */
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkMan = new LinkMan();
	
	private LinkManService linkManService;
	
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	//分页参数
	private Integer currPage = 1;
	private Integer pageSize = 3;

	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			this.currPage = 1;
		} else {
			this.currPage = currPage;
		}
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			this.pageSize = 3;
		} else {
			this.pageSize = pageSize;
		}
	}

	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		//设置条件
		if(linkMan.getLkm_name() != null) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
		}
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		//调用业务层
		PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String addUI() {
		//查询所有客户
		List<Customer> list = customerService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "addUI";
	}
	
	public String save() {
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	
	public String edit() {
		//查询所有客户
		List<Customer> list = customerService.findAll();
		//查询联系人
		linkMan = linkManService.findById(linkMan.getLkm_id());
		ActionContext.getContext().getValueStack().set("list", list);
		//压栈  数据自动回显
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}
	
	public String update() {
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	public String delete() {
		linkMan = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
}
