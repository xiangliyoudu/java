package com.xlyd.springboot.app_platform.service.backend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xlyd.springboot.app_platform.entity.DataDictionary;

public interface IDataDictionaryService {
	/**
	 * @return:DataDictionary中应用平台列表
	 */
	public List<DataDictionary> findListByTypeCode(@Param("typeCode") String typeCode);

}
