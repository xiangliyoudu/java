package cn.service.backend;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dao.IDataDictionaryDao;
import cn.pojo.DataDictionary;
@Service("IDataDictionaryServiceImpl")
public class IDataDictionaryServiceImpl implements IDataDictionaryService {
	
	@Resource
	private IDataDictionaryDao iDataDictionaryDao;
	@Override
	public List<DataDictionary> findListByTypeCode(String typeCode) {
		// TODO Auto-generated method stub
		return iDataDictionaryDao.findListByTypeCode(typeCode);
	}

}
