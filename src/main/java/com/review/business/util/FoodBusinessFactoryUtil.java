/**
 * 
 */
package com.review.business.util;

import java.util.List;

import com.review.business.FoodBusiness;
import com.review.model.Food;
import com.review.util.BeanUtil;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public class FoodBusinessFactoryUtil {

	// Design pattern - Singleton
	private static FoodBusiness _foodBusiness;

	public static FoodBusiness getFoodBusiness() {

		if (_foodBusiness == null) {
			_foodBusiness = BeanUtil.getBean(FoodBusiness.class);
		}
		return _foodBusiness;
	} // ============================

	public static Food findById(long foodId) {
		return getFoodBusiness().findById(foodId);
	}

	public static Food updateFood(long foodId, String name, long foodTypeId, String foodTypeName, long stateId,
			String stateName, long cityId, String cityName, long districtId, String districtName, long villageId,
			String villageName, String linkGoogleMap, String description, String location, int isActive,
			long ownerFoodId, UserContext userContext) {
		return getFoodBusiness().updateFood(foodId, name, foodTypeId, foodTypeName, stateId, stateName, cityId,
				cityName, districtId, districtName, villageId, villageName, linkGoogleMap, description, location,
				isActive, ownerFoodId, userContext);
	}

	public static Food createFood(String name, long foodTypeId, String foodTypeName, long stateId, String stateName,
			long cityId, String cityName, long districtId, String districtName, long villageId, String villageName,
			String linkGoogleMap, String description, String location, long ownerFoodId, UserContext userContext) {
		return getFoodBusiness().createFood(name, foodTypeId, foodTypeName, stateId, stateName, cityId, cityName,
				districtId, districtName, villageId, villageName, linkGoogleMap, description, location, ownerFoodId,
				userContext);
	}

	public static Food deleteFood(long foodId, UserContext userContext) {
		return getFoodBusiness().deleteFood(foodId, userContext);
	}

	public static Food actionFood(long foodId, int status, UserContext userContext) {
		return getFoodBusiness().actionFood(foodId, status, userContext);
	}

	public static void indexing(UserContext userContext) {
		getFoodBusiness().indexing(userContext);
	}

	public static List<Food> findMyFoods(Long ownerFoodId, UserContext userContext) {
		return getFoodBusiness().findMyFoods(ownerFoodId, userContext);
	}
}
