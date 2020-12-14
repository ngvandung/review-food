/**
 * 
 */
package com.review.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.review.business.VillageCategoryBusiness;
import com.review.model.VillageCategory;
import com.review.security.PermissionCheckerFactoryUtil;
import com.review.service.VillageCategoryService;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public class VillageCategoryBusinessImpl implements VillageCategoryBusiness {
	@Autowired
	private VillageCategoryService villageCategoryService;

	@Override
	public Iterable<VillageCategory> getVillageCategories(String villageName, Integer isActive, Long districtId,
			Integer start, Integer end) {
		return villageCategoryService.getVillageCategories(villageName, isActive, districtId, start, end);
	}

	@Override
	public VillageCategory updateVillageCategory(long villageId, String villageName, Integer isActive, long districtId,
			UserContext userContext) {
		VillageCategory villageCategory = villageCategoryService.findById(villageId);
		if (PermissionCheckerFactoryUtil.isOwner(userContext, villageCategory.getUserId())) {
			villageCategory = villageCategoryService.updateVillageCategory(villageId, villageName, isActive, districtId,
					userContext.getUser().getUserId());
		}
		return villageCategory;
	}

	@Override
	public VillageCategory createVillageCategory(String villageName, long districtId, UserContext userContext) {
		PermissionCheckerFactoryUtil.checkAuthentication(userContext);

		return villageCategoryService.createVillageCategory(villageName, districtId, userContext.getUser().getUserId());
	}

	@Override
	public VillageCategory deleteVillageCategory(long villageId, UserContext userContext) {
		PermissionCheckerFactoryUtil.checkAdministrator(userContext);

		return villageCategoryService.deleteVillageCategory(villageId);
	}

	@Override
	public VillageCategory findById(long villageId) {
		return villageCategoryService.findById(villageId);
	}

	@Override
	public void indexing(UserContext userContext) {
		villageCategoryService.indexing();	
	}
}
