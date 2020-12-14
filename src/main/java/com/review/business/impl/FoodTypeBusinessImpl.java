/**
 * 
 */
package com.review.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.review.business.FoodTypeBusiness;
import com.review.model.FoodType;
import com.review.security.PermissionCheckerFactoryUtil;
import com.review.service.FoodTypeService;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public class FoodTypeBusinessImpl implements FoodTypeBusiness {

	@Autowired
	private FoodTypeService foodTypeService;

	@Override
	public Iterable<FoodType> getFoodTypes(String foodTypeName, Integer start, Integer end) {
		return foodTypeService.getFoodTypes(foodTypeName, start, end);
	}

	@Override
	public FoodType updateFoodType(long foodTypeId, String foodTypeName, UserContext userContext) {
		PermissionCheckerFactoryUtil.checkAuthentication(userContext);

		return foodTypeService.updateFoodType(foodTypeId, foodTypeName, userContext.getUser().getUserId());
	}

	@Override
	public FoodType createFoodType(String foodTypeName, UserContext userContext) {
		PermissionCheckerFactoryUtil.checkAuthentication(userContext);

		return foodTypeService.createFoodType(foodTypeName, userContext.getUser().getUserId());
	}

	@Override
	public FoodType deleteFoodType(long foodTypeId, UserContext userContext) {
		PermissionCheckerFactoryUtil.checkAuthentication(userContext);

		return foodTypeService.deleteFoodType(foodTypeId);
	}

	@Override
	public FoodType findById(long foodTypeId) {
		return foodTypeService.findById(foodTypeId);
	}

	@Override
	public void indexing(UserContext userContext) {
		foodTypeService.indexing();
	}

}
