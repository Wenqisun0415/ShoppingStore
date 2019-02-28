package crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import crm.dao.LinkManDao;
import crm.domain.LinkMan;
import crm.domain.PageBean;
import crm.service.LinkManService;

/**
 * UserServiceImpl for LinkMan class
 * @author wenqisun
 *
 */
@Transactional
public class LinkManServiceImpl implements LinkManService {

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		//设置当前页
		pageBean.setCurrPage(currPage);
		//设置每页显示记录数
		pageBean.setPageSize(pageSize);
		//设置总记录数
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		Double totalPage = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		//设置集合
		Integer begin = (currPage - 1) * pageSize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
	
	
}
