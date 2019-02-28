package crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import crm.dao.SaleVisitDao;
import crm.domain.Customer;
import crm.domain.PageBean;
import crm.domain.SaleVisit;
import crm.service.SaleVisitService;

/**
 * SaleVisitServiceImpl for LinkMan class
 * @author wenqisun
 *
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		//set currPage
		pageBean.setCurrPage(currPage);
		//set pageSize
		pageBean.setPageSize(pageSize);
		//set total number of records
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//set total page number
		Double totalPage = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		//set list
		Integer begin = (currPage - 1) * pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public SaleVisit findById(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}

	@Override
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}
}
