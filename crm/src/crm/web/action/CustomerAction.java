package crm.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.domain.Customer;
import crm.domain.PageBean;
import crm.service.CustomerService;
import crm.utils.UploadUtils;

/**
 * Action class for Customer
 * @author wenqisun
 *
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	private CustomerService customerService;
	
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//property driven to get the page number
	private Integer currPage = 1;
	private Integer pageSize = 3;
	

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

	@Override
	public Customer getModel() {
		return customer;
	}
	
	//文件上传提供的三个属性
	private String uploadFileName;
	private File upload;
	private String uploadContentType;
	

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String addUI() {
		return "addUI";
	}
	
	public String save() throws IOException {
		//upload image
		if(upload != null) {
			String path = "/Users/wenqisun/Downloads/fileUpload";
			//random file name
			String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
			//separate dirctory
			String realPath = UploadUtils.getPath(uuidFileName);
			//create directory
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdirs();
			}
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//set value for cust_image
			customer.setCust_image(url + "/" + uuidFileName);
		}
		customerService.save(customer);
		return "saveSuccess";
	}
	
	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		
		//设置条件
		if(customer.getCust_name() != null) {
			detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		if(customer.getBaseDictSource() !=null && !"".equals(customer.getBaseDictSource().getDict_id())) {
			detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
		}
		if(customer.getBaseDictLevel() != null && !"".equals(customer.getBaseDictLevel().getDict_id())) {
			detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
		}
		if(customer.getBaseDictIndustry() != null && !"".equals(customer.getBaseDictIndustry().getDict_id())) {
			detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
		}
		
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String delete() {
		//先查询 再删除
		customer = customerService.findById(customer.getCust_id());
		//删除图片
		if(customer.getCust_image() != null) {
			File file = new File(customer.getCust_image());
			if(file.exists()) {
				file.delete();
			}
		}
		customerService.delete(customer);
		return "deleteSuccess";
	}
	
	public String edit() {
		//根据id查询 跳转 回显数据
		customer = customerService.findById(customer.getCust_id());
		//将customer回显到页面
		//两种方式：1.手动压栈 2.模型驱动对象，默认在栈顶
		//第一种方式 <s:property value="cust_name"/>
		//第二种方式 <s:property value="model.cust_name"/>
		ActionContext.getContext().getValueStack().push(customer);
		return "editSuccess";
	}
	
	public String update() throws IOException {
		//判断文件是否被选
		if(upload != null) {
			String cust_image = customer.getCust_image();
			//删除旧文件
			if(cust_image != null || !"".equals(cust_image)) {
				File file = new File(cust_image);
				file.delete();
			}
			//重新上传文件
			String path = "/Users/wenqisun/Downloads/fileUpload";
			//random file name
			String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
			//separate dirctory
			String realPath = UploadUtils.getPath(uuidFileName);
			//create directory
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdirs();
			}
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//set value for cust_image
			customer.setCust_image(url + "/" + uuidFileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}
}
