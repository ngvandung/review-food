/**
 * 
 */
package com.review.service;

import com.review.model.FoodType;

/**
 * @author ddung
 *
 */
public interface FoodTypeService {

	public Iterable<FoodType> getFoodTypes(String typeName, Integer start, Integer end);

	public FoodType findById(long foodTypeId);

	public FoodType updateFoodType(long foodTypeId, String typeName, long userId);

	public FoodType createFoodType(String typeName, long userId);

	public FoodType deleteFoodType(long foodTypeId);

	public void indexing();
}
