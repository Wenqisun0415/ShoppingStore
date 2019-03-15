package store.service.impl;

import java.util.List;

import redis.clients.jedis.Jedis;
import store.dao.CategoryDao;
import store.dao.impl.CategoryDaoImpl;
import store.domain.Category;
import store.service.CategoryService;
import store.util.JedisUtils;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> getCats() throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		List<Category> list = dao.findAll();
		return list;
	}

	@Override
	public void add(Category category) throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		dao.add(category);
		//update redis cache
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("cats");
		JedisUtils.closeJedis(jedis);
	}

	@Override
	public void deleteById(String cid) throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		dao.deleteById(cid);
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("cats");
		JedisUtils.closeJedis(jedis);
	}

}
