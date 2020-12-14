/**
 * 
 */
package com.review.business;

import java.util.List;

import com.review.model.Food;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public interface FoodBusiness {
	public Food findById(long foodId);

	public Food updateFood(long foodId, String name, long foodTypeId, String typeName, long stateId, String stateName,
			long cityId, String cityName, long districtId, String districtName, long villageId, String villageName,
			String linkGoogleMap, String description, String location, int isActive, long ownerFoodId,
			UserContext userContext);

	public Food createFood(String name, long foodTypeId, String typeName, long stateId, String stateName, long cityId,
			String cityName, long districtId, String districtName, long villageId, String villageName,
			String linkGoogleMap, String description, String location, long ownerFoodId, UserContext userContext);

	public Food deleteFood(long foodId, UserContext userContext);

	public Food actionFood(long foodId, int status, UserContext userContext);

	public void indexing(UserContext userContext);

	public List<Food> findMyFoods(Long ownerFoodId, UserContext userContext);

}
