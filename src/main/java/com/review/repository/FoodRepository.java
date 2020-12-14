/**
 * 
 */
package com.review.repository;

import java.util.List;

import com.review.model.Food;

/**
 * @author ddung
 *
 */
public interface FoodRepository {
	public Food findById(long foodId);

	public Food updateFood(Food food);

	public Food createFood(Food food);

	public Food deleteFood(long foodId);

	public List<Food> findAll();

	public List<Food> findMyFoods(Long ownerFoodId);
}
