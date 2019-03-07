package store.service.impl;

import java.util.List;

import store.dao.CategoryDao;
import store.dao.impl.CategoryDaoImpl;
import store.domain.Category;
import store.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> getCats() throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		List<Category> list = dao.findAll();
		return list;
	}

}
