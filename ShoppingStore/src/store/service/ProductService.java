package store.service;

import java.util.List;

import store.domain.PageBean;
import store.domain.Product;

public interface ProductService {

	public List<Product> findHot() throws Exception;
	
	public List<Product> findNew() throws Exception;
	
	public Product findProductByPid(String pid) throws Exception;
	
	public PageBean<Product> findProductsByCat(String cid, int pageNum) throws Exception;
}
