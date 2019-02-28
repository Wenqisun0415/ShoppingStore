package crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import crm.dao.CustomerDao;
import crm.domain.Customer;
import crm.domain.PageBean;
import crm.service.CustomerService;

/**
 * CustomerServiceImpl for Customer class
 * @author wenqisun
 *
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		//set currPage
		pageBean.setCurrPage(currPage);
		//set pageSize
		pageBean.setPageSize(pageSize);
		//set total number of records
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//set total page number
		Double totalPage = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		//set list
		Integer begin = (currPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	
	
}
