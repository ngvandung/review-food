/**
 * 
 */
package com.review.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.review.business.UserRoleBusiness;
import com.review.model.UserRole;
import com.review.security.PermissionCheckerFactoryUtil;
import com.review.service.UserRoleService;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
@Service
public class UserRoleBusinessImpl implements UserRoleBusiness {

	@Autowired
	private UserRoleService userRoleService;

	@Override
	public UserRole updateUserRole(int roleId, long userId, UserContext userContext) {
		PermissionCheckerFactoryUtil.checkAdministrator(userContext);
		
		UserRole userRole = userRoleService.updateUserRole(roleId, userId);

		return userRole;
	}

}
