package store.service.impl;

import java.util.List;

import store.dao.ProductDao;
import store.dao.impl.ProductDaoImpl;
import store.domain.PageBean;
import store.domain.Product;
import store.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> findHot() throws Exception{
		ProductDao dao = new ProductDaoImpl();
		return dao.findHot();
	}

	@Override
	public List<Product> findNew() throws Exception{
		ProductDao dao = new ProductDaoImpl();
		return dao.findNew();
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		ProductDao dao = new ProductDaoImpl();
		return dao.findProductByPid(pid);
	}

	@Override
	public PageBean<Product> findProductsByCat(String cid, int pageNum) throws Exception {
		ProductDao dao = new ProductDaoImpl();
		List<Product> products = dao.findProductByCidWithPage(cid, pageNum, 12);
		int count = dao.findProductNumByCid(cid);
		PageBean<Product> pageBean = new PageBean<Product>(pageNum, 12, count);
		pageBean.setUrl("ProductServlet?method=findProductsByCat&cid=" + cid);
		pageBean.setList(products);
		return pageBean;
	}

	@Override
	public PageBean findAllWithPage(int currNum) throws Exception {
		ProductDao dao = new ProductDaoImpl();
		int totalRecords = dao.findTotalRecords();
		PageBean pageBean = new PageBean(currNum, 5, totalRecords);
		List<Product> list = dao.findAllWithPage(pageBean.getStartPage(), pageBean.getPageSize());
		pageBean.setList(list);
		pageBean.setUrl("AdminProductServlet?method=findAllWithPage");
		return pageBean;
	}

}
