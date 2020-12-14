/**
 * 
 */
package com.review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.review.business.util.FoodBusinessFactoryUtil;
import com.review.model.Food;
import com.review.util.BeanUtil;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
@RestController
@RequestMapping("/api/v1")
public class FoodController {

	@RequestMapping(value = "/food", method = RequestMethod.POST)
	@ResponseBody
	public Food createFood(HttpServletRequest request, HttpSession session, @RequestBody Food food) {
		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		return FoodBusinessFactoryUtil.createFood(food.getName(), food.getFoodTypeId(), food.getFoodTypeName(),
				food.getStateId(), food.getStateName(), food.getCityId(), food.getCityName(), food.getDistrictId(),
				food.getDistrictName(), food.getVillageId(), food.getVillageName(), food.getLinkGoogleMap(),
				food.getDescription(), food.getLocation(), userContext.getUser().getUserId(), userContext);
	}

	@RequestMapping(value = "/food/indexing", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> indexing(HttpServletRequest request, HttpSession session) {
		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		FoodBusinessFactoryUtil.indexing(userContext);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 200);
		return result;
	}

	@RequestMapping(value = "/food", method = RequestMethod.PUT)
	@ResponseBody
	public Food updateFood(HttpServletRequest request, HttpSession session, @RequestBody Food food) {

		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		return FoodBusinessFactoryUtil.updateFood(food.getFoodId(), food.getName(), food.getFoodTypeId(),
				food.getFoodTypeName(), food.getStateId(), food.getStateName(), food.getCityId(), food.getCityName(),
				food.getDistrictId(), food.getDistrictName(), food.getVillageId(), food.getVillageName(),
				food.getLinkGoogleMap(), food.getDescription(), food.getLocation(), food.getIsActive(),
				userContext.getUser().getUserId(), userContext);

	}

	@RequestMapping(value = "/food/{foodId}", method = RequestMethod.DELETE)
	@ResponseBody
	public Food deleteFood(HttpServletRequest request, HttpSession session, @PathVariable("foodId") Long foodId) {

		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		return FoodBusinessFactoryUtil.deleteFood(foodId, userContext);
	}

	@RequestMapping(value = "/food/{foodId}", method = RequestMethod.GET)
	@ResponseBody
	public Food findById(HttpServletRequest request, HttpSession session, @PathVariable("foodId") Long foodId) {

		return FoodBusinessFactoryUtil.findById(foodId);
	}

	@RequestMapping(value = "/foods", method = RequestMethod.GET)
	@ResponseBody
	public List<Food> findMyFoods(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "ownerFoodId", required = false) Long ownerFoodId) {

		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		return FoodBusinessFactoryUtil.findMyFoods(ownerFoodId, userContext);
	}

	@RequestMapping(value = "/food/action/{foodId}", method = RequestMethod.PUT)
	@ResponseBody
	public Food actionFood(HttpServletRequest request, HttpSession session, @PathVariable("foodId") Long foodId,
			@RequestParam("status") int status) {

		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		return FoodBusinessFactoryUtil.actionFood(foodId, status, userContext);
	}

}
