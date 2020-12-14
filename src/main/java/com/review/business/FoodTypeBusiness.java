/**
 * 
 */
package com.review.business;

import com.review.model.FoodType;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public interface FoodTypeBusiness {
	public Iterable<FoodType> getFoodTypes(String foodTypeName, Integer start, Integer end);

	public FoodType updateFoodType(long foodTypeId, String foodTypeName, UserContext userContext);

	public FoodType createFoodType(String foodTypeName, UserContext userContext);

	public FoodType deleteFoodType(long foodTypeId, UserContext userContext);

	public FoodType findById(long foodTypeId);
	
	public void indexing(UserContext userContext);
}
