package crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import crm.dao.CustomerDao;
import crm.domain.Customer;

/**
 * DaoImpl for Customer class
 * @author wenqisun
 *
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

}
