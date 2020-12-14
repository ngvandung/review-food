/**
 * 
 */
package com.review.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.review.business.FoodBusiness;
import com.review.exception.ForbiddenException;
import com.review.model.Food;
import com.review.model.User;
import com.review.security.PermissionCheckerFactoryUtil;
import com.review.service.FoodService;
import com.review.service.UserService;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public class FoodBusinessImpl implements FoodBusiness {

	@Autowired
	private FoodService foodService;
	@Autowired
	private UserService userService;

	@Override
	public Food findById(long foodId) {
		return foodService.findById(foodId);
	}

	@Override
	public Food updateFood(long foodId, String name, long foodTypeId, String typeName, long stateId, String stateName,
			long cityId, String cityName, long districtId, String districtName, long villageId, String villageName,
			String linkGoogleMap, String description, String location, int isActive, long ownerFoodId,
			UserContext userContext) {

		User user = userService.findByUserId(userContext.getUser().getUserId());
		if (user.getIsEnabled() != 1) {
			throw new ForbiddenException();
		}

		Food food = foodService.findById(foodId);
		if (PermissionCheckerFactoryUtil.isOwner(userContext, food.getUserId())) {
			return foodService.updateFood(foodId, name, foodTypeId, typeName, stateId, stateName, cityId, cityName,
					districtId, districtName, villageId, villageName, linkGoogleMap, description, location, isActive,
					ownerFoodId, userContext.getUser().getUserId());
		} else {
			throw new ForbiddenException();
		}
	}

	@Override
	public Food createFood(String name, long foodTypeId, String typeName, long stateId, String stateName, long cityId,
			String cityName, long districtId, String districtName, long villageId, String villageName,
			String linkGoogleMap, String description, String location, long ownerFoodId, UserContext userContext) {

		User user = userService.findByUserId(userContext.getUser().getUserId());
		if (user.getIsEnabled() != 1) {
			throw new ForbiddenException();
		}
		PermissionCheckerFactoryUtil.checkHost(userContext);

		return foodService.createFood(name, foodTypeId, typeName, stateId, stateName, cityId, cityName, districtId,
				districtName, villageId, villageName, linkGoogleMap, description, location, ownerFoodId,
				userContext.getUser().getUserId());
	}

	@Override
	public Food deleteFood(long foodId, UserContext userContext) {
		User user = userService.findByUserId(userContext.getUser().getUserId());
		if (user.getIsEnabled() != 1) {
			throw new ForbiddenException();
		}
		Food food = foodService.findById(foodId);
		if (PermissionCheckerFactoryUtil.isOwner(userContext, food.getUserId())) {
			return foodService.deleteFood(foodId);
		} else {
			throw new ForbiddenException();
		}
	}

	@Override
	public Food actionFood(long foodId, int status, UserContext userContext) {
		PermissionCheckerFactoryUtil.checkManager(userContext);

		Food food = findById(foodId);
		if (food != null) {
			food.setIsActive(status);
			food.setModifiedDate(new Date());

			food = foodService.updateFood(food);
		}
		return food;
	}

	@Override
	public void indexing(UserContext userContext) {
		foodService.indexing();
	}

	@Override
	public List<Food> findMyFoods(Long ownerFoodId, UserContext userContext) {
		if (PermissionCheckerFactoryUtil.isOwner(userContext, ownerFoodId)) {
			return foodService.findMyFoods(ownerFoodId);
		} else {
			throw new ForbiddenException();
		}
	}

}
