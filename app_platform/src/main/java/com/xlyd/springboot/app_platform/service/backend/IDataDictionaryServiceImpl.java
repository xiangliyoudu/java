package com.xlyd.springboot.app_platform.service.backend;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xlyd.springboot.app_platform.dao.IDataDictionaryDao;
import com.xlyd.springboot.app_platform.entity.DataDictionary;
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
