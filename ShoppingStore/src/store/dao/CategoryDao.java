package store.dao;

import java.util.List;

import store.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void add(Category category) throws Exception;

	void deleteById(String cid) throws Exception;

}
