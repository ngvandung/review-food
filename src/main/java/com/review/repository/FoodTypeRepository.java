/**
 * 
 */
package com.review.repository;

import java.util.List;

import com.review.model.FoodType;

/**
 * @author ddung
 *
 */
public interface FoodTypeRepository {

	public Iterable<FoodType> getFoodTypes(String typeName, Integer start, Integer end);

	public FoodType findById(long foodTypeId);

	public FoodType updateFoodType(FoodType foodType);

	public FoodType createFoodType(FoodType foodType);

	public FoodType deleteFoodType(long foodTypeId);

	public List<FoodType> findAll();
}
