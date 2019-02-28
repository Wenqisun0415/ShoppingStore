package crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 通用DAO接口
 * @author wenqisun
 *
 */
public interface BaseDao<T> {
	
	public void save(T t);
	
	public void delete(T t);
	
	public void update(T t);
	
	public T findById(Serializable id);
	
	public List<T> findAll();
	
	public Integer findCount(DetachedCriteria detachedCriteria);
	
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);
	
}
