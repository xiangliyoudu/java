package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pojo.User;
import utils.PasswordUtil;

@Repository
public class UserDao {
	
	@Autowired
	JdbcTemplate template;
	
	public User findByUsername(String username){
		String sql = "select * from sys_users where username = ?";
		BeanPropertyRowMapper<User> rowMapper = 
				BeanPropertyRowMapper.newInstance(User.class);
		User user = template.queryForObject(sql, rowMapper, username);
		return user;
	}
	
	public void save(User user) {
		String sql = "insert into sys_users (username, password, salt) values(?, ?, ?)";
		String salt = user.getUsername();
		user.setPassword(PasswordUtil.encryptPassword(user.getPassword(), salt));
		template.update(sql, user.getUsername(), user.getPassword(), salt);
	}

}







