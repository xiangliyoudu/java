package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Person;
import pojo.User;

public interface PersonDao {
	/**
	 * insert list
	 * @param cells
	 */
	public void insertList(List<User> users);
	
	/**list all
	 * @return
	 */
	public List<User> queryAll();
	
	public User queryById(@Param("id") Integer id);
	
	/** batch select
	 * @param ids
	 * @return
	 */
	public List<User> getForeach(@Param("ids") List<Integer> ids);
	
	/**batch add
	 * @param users
	 * @return
	 */
	public int addForeach(@Param("users") List<Person> users);
	
	/**batch delete
	 * @param ids
	 * @return
	 */
	public int deleteForeach(@Param("ids") List<Integer> ids);
	
} 






