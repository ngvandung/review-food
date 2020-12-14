/**
 * 
 */
package com.review.business;

import com.review.model.UserRole;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public interface UserRoleBusiness {
	public UserRole updateUserRole(int roleId, long userId, UserContext userContext);
}
