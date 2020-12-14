/**
 * 
 */
package com.review.repository;

import com.review.model.Role;

/**
 * @author ddung
 *
 */
public interface RoleRepository {
	public Role findByRoleId(int roleId);
}
