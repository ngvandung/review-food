/**
 * 
 */
package com.review.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.review.business.util.FoodTypeBusinessFactoryUtil;
import com.review.model.FoodType;
import com.review.util.BeanUtil;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
@RestController
@RequestMapping("/api/v1")
public class FoodTypeController {
	@RequestMapping(value = "/foodtypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<FoodType> getFoodTypes(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "foodTypeName", required = false) String foodTypeName,
			@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "end", required = false) Integer end) {

		Iterable<FoodType> foodTypes = FoodTypeBusinessFactoryUtil.getFoodTypes(foodTypeName, start, end);

		return foodTypes;
	}

	@RequestMapping(value = "/foodtype", method = RequestMethod.POST)
	@ResponseBody
	public FoodType createFoodType(HttpServletRequest request, HttpSession session, @RequestBody FoodType foodType)
			throws IOException {

		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		return FoodTypeBusinessFactoryUtil.createFoodType(foodType.getFoodTypeName(), userContext);

	}

	@RequestMapping(value = "/foodtype/indexing", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> indexing(HttpServletRequest request, HttpSession session) {

		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		FoodTypeBusinessFactoryUtil.indexing(userContext);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 200);
		return result;

	}

	@RequestMapping(value = "/foodtype", method = RequestMethod.PUT)
	@ResponseBody
	public FoodType updateFoodType(HttpServletRequest request, HttpSession session, @RequestBody FoodType foodType)
			throws IOException {

		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		return FoodTypeBusinessFactoryUtil.updateFoodType(foodType.getFoodTypeId(), foodType.getFoodTypeName(),
				userContext);

	}

	@RequestMapping(value = "/foodtype/{foodTypeId}", method = RequestMethod.DELETE)
	@ResponseBody
	public FoodType deleteFoodType(HttpServletRequest request, HttpSession session,
			@PathVariable("foodTypeId") Long foodTypeId) throws IOException {

		UserContext userContext = BeanUtil.getBean(UserContext.class);
		userContext = (UserContext) session.getAttribute("userContext");

		return FoodTypeBusinessFactoryUtil.deleteFoodType(foodTypeId, userContext);
	}
}
