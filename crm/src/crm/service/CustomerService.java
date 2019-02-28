package crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import crm.domain.Customer;
import crm.domain.PageBean;

/**
 * Service interface for Customer class
 * @author wenqisun
 *
 */
public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);

	List<Customer> findAll();

}
