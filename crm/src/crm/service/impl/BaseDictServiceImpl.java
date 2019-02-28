package crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.BaseDictDao;
import crm.domain.BaseDict;
import crm.service.BaseDictService;

/**
 * CustomerServiceImpl for BaseDict class
 * @author wenqisun
 *
 */
@Transactional
public class BaseDictServiceImpl implements BaseDictService {

	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> getByTypeCode(String dict_type_code) {
		return baseDictDao.getByTypeCode(dict_type_code);
	}
	
	
}
