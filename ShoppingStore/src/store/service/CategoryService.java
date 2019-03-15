package store.service;

import java.util.List;

import store.domain.Category;

public interface CategoryService {

	List<Category> getCats() throws Exception;

	void add(Category category) throws Exception;

	void deleteById(String cid) throws Exception;


}
