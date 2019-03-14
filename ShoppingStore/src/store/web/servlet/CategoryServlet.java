package store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;
import store.domain.Category;
import store.service.CategoryService;
import store.service.impl.CategoryServiceImpl;
import store.util.JedisUtils;
import store.web.base.BaseServlet;

public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public String getCats(HttpServletRequest req, HttpServletResponse resp) {
		
    	try {
    		Jedis jedis = JedisUtils.getJedis();
    		String jsonStr = null;
    		jsonStr = jedis.get("cats");
    		if(jsonStr == null || "".equals(jsonStr)) {
    			
    			CategoryService service = new CategoryServiceImpl();
    			List<Category> cats = service.getCats();
    			
    			jsonStr = JSONArray.fromObject(cats).toString();
    			
    			
    			//put into redis
    			jedis.set("cats", jsonStr);
    		}

			//set content type to json format
			resp.setContentType("application/json; charset=utf-8");
			resp.getWriter().write(jsonStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
    	return null;
    }

}
