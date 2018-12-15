package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import pojo.User;
import dao.UserDao;

public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	UserDao dao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		
		User user = dao.findByUsername(username);
		
		if (null == user) {
			throw new UnknownAccountException();
		}
		if (user.getLocked() == 1) {
			throw new LockedAccountException();
		}
		
		return new SimpleAuthenticationInfo(user.getUsername(), 
				user.getPassword(), this.getName());
	}

}








