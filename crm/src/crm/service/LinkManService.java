package crm.service;

import org.hibernate.criterion.DetachedCriteria;

import crm.domain.LinkMan;
import crm.domain.PageBean;

/**
 * Service interface for User class
 * @author wenqisun
 *
 */
public interface LinkManService {

	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
