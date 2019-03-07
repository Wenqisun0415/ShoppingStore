package store.dao;

import java.util.List;

import store.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

}
