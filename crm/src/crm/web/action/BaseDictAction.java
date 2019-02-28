package crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.domain.BaseDict;
import crm.service.BaseDictService;
import net.sf.json.JSONArray;

/**
 * Action class for BaseDict
 * @author wenqisun
 *
 */
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

	private BaseDict baseDict = new BaseDict();
	private BaseDictService baseDictService;
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	
	public String findByTypeCode() throws IOException {
		List<BaseDict> list = baseDictService.getByTypeCode(baseDict.getDict_type_code());
		//convert from list to json
		/**
		 * JSONArray: 将数组和list集合转json
		 * JSONObject: 将对象和Map集合转json
		 */
		JSONArray jsonArray = JSONArray.fromObject(list);
		//将数据打印到页面
		ServletActionContext.getResponse().setContentType("text/html; charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}

}
