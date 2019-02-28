package crm.dao;

import java.util.List;

import crm.domain.BaseDict;

/**
 * Dao interface for BaseDict class
 * @author wenqisun
 *
 */
public interface BaseDictDao {

	List<BaseDict> getByTypeCode(String dict_type_code);

}
