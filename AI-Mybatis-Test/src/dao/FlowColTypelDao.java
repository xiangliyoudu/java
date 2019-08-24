package dao;

import java.util.List;
import java.util.Map;

import pojo.ColType;

public interface FlowColTypelDao {

	void save(ColType bo);

	void update(ColType bo);

	void delete(int id);

	void deleteByItemId(int id);

	List<ColType> query(Map<String, Object> params);

	ColType queryById(int id);

	ColType queryByItemId(int itemId);
}
