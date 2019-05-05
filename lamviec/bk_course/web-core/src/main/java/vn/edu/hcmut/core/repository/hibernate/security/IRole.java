package vn.edu.hcmut.core.repository.hibernate.security;

import java.util.List;

import vn.edu.hcmut.core.model.hibernate.security.Role;

public interface IRole {
	Role findRoleByName(String roleName) throws Exception;
	Role findRoleById(Long id) throws Exception;
	List<Role> getListRole() throws Exception;
}
