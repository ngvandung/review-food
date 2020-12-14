/**
 * 
 */
package com.review.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.review.constant.FoodConstant;
import com.review.model.Food;
import com.review.service.FoodService;

/**
 * @author ddung
 *
 */
public class QueryUtil {

	private final static String HOUSE = Food.class.getName();
	@Autowired
	private FoodService foodService;

	public Map<String, Object> getByClassPK_ClassName(long classPK, String className) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (className.equals(HOUSE)) {
			Food food = foodService.findById(classPK);
			if (food.getIsActive() == FoodConstant.ACTIVE) {
				result.put("classPK", food.getFoodId());
				result.put("className", Food.class.getName());
				result.put("ownerId", food.getOwnerFoodId());
			}
		}
		return result;
	}
}
