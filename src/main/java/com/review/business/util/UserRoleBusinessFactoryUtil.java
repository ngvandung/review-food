/**
 * 
 */
package com.review.business.util;

import com.review.business.UserRoleBusiness;
import com.review.model.UserRole;
import com.review.util.BeanUtil;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public class UserRoleBusinessFactoryUtil {
	// Design pattern - Singleton
	private static UserRoleBusiness _userRoleBusiness;

	public static UserRoleBusiness getUserRoleBusiness() {

		if (_userRoleBusiness == null) {
			_userRoleBusiness = BeanUtil.getBean(UserRoleBusiness.class);
		}
		return _userRoleBusiness;
	}
	// ============================

	public static UserRole updateUserRole(int roleId, long userId, UserContext userContext) {
		return getUserRoleBusiness().updateUserRole(roleId, userId, userContext);
	}
}
