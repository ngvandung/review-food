/**
 * 
 */
package com.review.service;

import java.util.List;

import com.review.model.Food;

/**
 * @author ddung
 *
 */
public interface FoodService {
	public Food findById(long foodId);

	public Food updateFood(long foodId, String name, long foodTypeId, String typeName, long stateId, String stateName,
			long cityId, String cityName, long districtId, String districtName, long villageId, String villageName,
			String linkGoogleMap, String description, String location, int isActive, long ownerFoodId, long userId);

	public Food createFood(String name, long foodTypeId, String typeName, long stateId, String stateName, long cityId,
			String cityName, long districtId, String districtName, long villageId, String villageName,
			String linkGoogleMap, String description, String location, long ownerFoodId, long userId);

	public Food deleteFood(long foodId);

	public Food updateFood(Food food);

	public void indexing();

	public List<Food> findMyFoods(Long ownerFoodId);
}
