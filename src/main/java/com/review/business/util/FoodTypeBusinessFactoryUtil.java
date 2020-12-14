/**
 * 
 */
package com.review.business.util;

import com.review.business.FoodTypeBusiness;
import com.review.model.FoodType;
import com.review.util.BeanUtil;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public class FoodTypeBusinessFactoryUtil {
	// Design pattern - Singleton
	private static FoodTypeBusiness _houseTypeBusiness;

	public static FoodTypeBusiness getFoodTypeBusiness() {

		if (_houseTypeBusiness == null) {
			_houseTypeBusiness = BeanUtil.getBean(FoodTypeBusiness.class);
		}
		return _houseTypeBusiness;
	}
	// ============================

	public static Iterable<FoodType> getFoodTypes(String foodTypeName, Integer start, Integer end) {
		return getFoodTypeBusiness().getFoodTypes(foodTypeName, start, end);
	}

	public static FoodType updateFoodType(long houseTypeId, String foodTypeName, UserContext userContext) {
		return getFoodTypeBusiness().updateFoodType(houseTypeId, foodTypeName, userContext);
	}

	public static FoodType createFoodType(String foodTypeName, UserContext userContext) {
		return getFoodTypeBusiness().createFoodType(foodTypeName, userContext);
	}

	public static FoodType deleteFoodType(long houseTypeId, UserContext userContext) {
		return getFoodTypeBusiness().deleteFoodType(houseTypeId, userContext);
	}

	public static FoodType findById(long houseTypeId) {
		return getFoodTypeBusiness().findById(houseTypeId);
	}

	public static void indexing(UserContext userContext) {
		getFoodTypeBusiness().indexing(userContext);
	}
}
