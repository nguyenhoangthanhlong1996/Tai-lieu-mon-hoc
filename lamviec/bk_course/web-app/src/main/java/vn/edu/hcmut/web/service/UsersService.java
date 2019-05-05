package vn.edu.hcmut.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.edu.hcmut.core.model.hibernate.security.Privilege;
import vn.edu.hcmut.core.model.hibernate.security.Role;
import vn.edu.hcmut.core.model.hibernate.security.User;
import vn.edu.hcmut.core.repository.hibernate.security.IUser;

@Service("usersService")
@Transactional(rollbackOn = Exception.class)
public class UsersService implements UserDetailsService {

	@Autowired
	IUser userHibernateDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userHibernateDAO.findUserByUserName(userName);
			if (user == null) {
				throw new UsernameNotFoundException("Tài khoản " + userName + " không tìm thấy");
			}
			if (!user.isActive()) {
				throw new UsernameNotFoundException("Tài khoản " + userName + " đang bị khóa");
			}
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPasswordLogin(),
					user.isActive(), true, true, true, getAuthorities(user.getRoles()));
		} catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	private List<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles), roles);
	}

	private List<String> getPrivileges(List<Role> roles) {
		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getPrivilegeName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges, List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

}