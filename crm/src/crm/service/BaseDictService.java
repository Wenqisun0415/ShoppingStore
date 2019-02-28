package crm.service;

import java.util.List;

import crm.domain.BaseDict;

/**
 * Service interface for BaseDict class
 * @author wenqisun
 *
 */
public interface BaseDictService {

	List<BaseDict> getByTypeCode(String dict_type_code);

}
