/**
 * 
 */
package com.review.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import com.review.constant.FoodConstant;
import com.review.model.Food;
import com.review.repository.FoodRepository;
import com.review.service.CounterService;
import com.review.service.FoodService;

/**
 * @author ddung
 *
 */
@Service
public class FoodServiceImpl implements FoodService {

	private static final Logger log = Logger.getLogger(FoodServiceImpl.class);

	@Autowired
	private FoodRepository foodRepository;
	@Resource
	private ElasticsearchOperations elasticsearchOperations;
	@Autowired
	private CounterService counterService;

	@Override
	public Food findById(long foodId) {
		return foodRepository.findById(foodId);
	}

	@Override
	public Food updateFood(long foodId, String name, long foodTypeId, String foodTypeName, long stateId,
			String stateName, long cityId, String cityName, long districtId, String districtName, long villageId,
			String villageName, String linkGoogleMap, String description, String location, int isActive,
			long ownerFoodId, long userId) {
		Food food = foodRepository.findById(foodId);

		if (food != null) {
			food.setDescription(description);
			food.setFoodTypeId(foodTypeId);
			food.setFoodTypeName(foodTypeName);
			food.setStateId(stateId);
			food.setStateName(stateName);
			food.setCityId(cityId);
			food.setCityName(cityName);
			food.setDistrictId(districtId);
			food.setDistrictName(districtName);
			food.setVillageId(villageId);
			food.setVillageName(villageName);
			food.setLinkGoogleMap(linkGoogleMap);
			food.setIsActive(isActive);
			food.setName(name);
			food.setModifiedDate(new Date());
			food.setOwnerFoodId(ownerFoodId);
			food.setUserId(userId);
			food.setLocation(location);

			food = foodRepository.updateFood(food);
			if (food != null) {
				IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(food.getFoodId()))
						.withObject(food).build();
				String documentId = elasticsearchOperations.index(indexQuery);
				log.info("documentId: " + documentId);
			}
		}

		return food;
	}

	@Override
	public Food createFood(String name, long foodTypeId, String foodTypeName, long stateId, String stateName,
			long cityId, String cityName, long districtId, String districtName, long villageId, String villageName,
			String linkGoogleMap, String description, String location, long ownerFoodId, long userId) {
		Food food = new Food();
		long foodId = counterService.increment(Food.class.getName());

		food.setFoodId(foodId);
		food.setDescription(description);
		food.setFoodTypeId(foodTypeId);
		food.setFoodTypeName(foodTypeName);
		food.setIsActive(FoodConstant.PENDING); // If is new food, it will waiting for accept
		food.setStateId(stateId);
		food.setStateName(stateName);
		food.setCityId(cityId);
		food.setCityName(cityName);
		food.setDistrictId(districtId);
		food.setDistrictName(districtName);
		food.setVillageId(villageId);
		food.setVillageName(villageName);
		food.setLinkGoogleMap(linkGoogleMap);
		food.setName(name);
		food.setModifiedDate(new Date());
		food.setCreateDate(new Date());
		food.setOwnerFoodId(ownerFoodId);
		food.setUserId(userId);
		food.setLocation(location);

		food = foodRepository.createFood(food);
		if (food != null) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(food.getFoodId())).withObject(food)
					.build();
			String documentId = elasticsearchOperations.index(indexQuery);
			log.info("documentId: " + documentId);
		}

		return food;
	}

	@Override
	public Food deleteFood(long foodId) {
		Food food = foodRepository.deleteFood(foodId);
		if (food != null) {
			String documentId = elasticsearchOperations.delete(Food.class, String.valueOf(foodId));
			log.info("documentId: " + documentId);
		}
		return food;
	}

	@Override
	public Food updateFood(Food food) {
		food = foodRepository.updateFood(food);
		if (food != null) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(food.getFoodId())).withObject(food)
					.build();
			String documentId = elasticsearchOperations.index(indexQuery);
			log.info("documentId: " + documentId);
		}
		return food;
	}

	@Override
	public void indexing() {
		List<Food> foods = foodRepository.findAll();
		for (int i = foods.size() - 1; i >= 0; i--) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(foods.get(i).getFoodId()))
					.withObject(foods.get(i)).build();
			String documentId = elasticsearchOperations.index(indexQuery);
			log.info("documentId: " + documentId);
		}
	}

	@Override
	public List<Food> findMyFoods(Long ownerFoodId) {
		return foodRepository.findMyFoods(ownerFoodId);
	}

}
