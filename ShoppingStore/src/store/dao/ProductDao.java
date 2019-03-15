package store.dao;

import java.util.List;

import store.domain.Product;

public interface ProductDao {
	
	public List<Product> findHot() throws Exception;
	
	public List<Product> findNew() throws Exception;

	public Product findProductByPid(String pid) throws Exception;
	
	public List<Product> findProductByCidWithPage(String cid, int pageNum, int pageSize) throws Exception;
	
	public int findProductNumByCid(String cid) throws Exception;

	public int findTotalRecords() throws Exception;

	public List<Product> findAllWithPage(int startPage, int pageSize) throws Exception;
}
