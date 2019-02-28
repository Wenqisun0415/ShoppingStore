package crm.service;

import org.hibernate.criterion.DetachedCriteria;

import crm.domain.PageBean;
import crm.domain.SaleVisit;

/**
 * SaleVisit interface for User class
 * @author wenqisun
 *
 */
public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

	SaleVisit findById(String visit_id);

	void delete(SaleVisit saleVisit);

}
