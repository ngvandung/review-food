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

import com.review.model.FoodType;
import com.review.repository.FoodTypeRepository;
import com.review.service.CounterService;
import com.review.service.FoodTypeService;

/**
 * @author ddung
 *
 */
@Service
public class FoodTypeServiceImpl implements FoodTypeService {

	private static final Logger log = Logger.getLogger(FoodTypeServiceImpl.class);

	@Autowired
	private FoodTypeRepository foodTypeRepository;

	@Resource
	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private CounterService counterService;

	@Override
	public FoodType findById(long foodTypeId) {
		return foodTypeRepository.findById(foodTypeId);
	}

	@Override
	public FoodType updateFoodType(long foodTypeId, String foodFoodTypeName, long userId) {
		FoodType foodType = foodTypeRepository.findById(foodTypeId);

		if (foodType != null) {
			foodType.setFoodTypeName(foodFoodTypeName);
			foodType.setModifiedDate(new Date());
			foodType.setUserId(userId);

			foodType = foodTypeRepository.updateFoodType(foodType);
			if (foodType != null) {
				IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(foodType.getFoodTypeId()))
						.withObject(foodType).build();
				String documentId = elasticsearchOperations.index(indexQuery);
				log.info("documentId: " + documentId);
			}
		}

		return foodType;
	}

	@Override
	public FoodType createFoodType(String foodFoodTypeName, long userId) {
		FoodType foodType = new FoodType();

		long foodTypeId = counterService.increment(FoodType.class.getName());

		foodType.setFoodTypeId(foodTypeId);
		foodType.setFoodTypeName(foodFoodTypeName);
		foodType.setCreateDate(new Date());
		foodType.setModifiedDate(new Date());
		foodType.setUserId(userId);

		foodType = foodTypeRepository.createFoodType(foodType);
		if (foodType != null) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(foodType.getFoodTypeId()))
					.withObject(foodType).build();
			String documentId = elasticsearchOperations.index(indexQuery);
			log.info("documentId: " + documentId);
		}

		return foodType;
	}

	@Override
	public FoodType deleteFoodType(long foodTypeId) {
		FoodType foodType = foodTypeRepository.deleteFoodType(foodTypeId);
		if (foodType != null) {
			String documentId = elasticsearchOperations.delete(FoodType.class, String.valueOf(foodTypeId));
			log.info("documentId: " + documentId);
		}
		return foodType;
	}

	@Override
	public Iterable<FoodType> getFoodTypes(String foodFoodTypeName, Integer start, Integer end) {
		return foodTypeRepository.getFoodTypes(foodFoodTypeName, start, end);
	}

	@Override
	public void indexing() {
		List<FoodType> foodTypes = foodTypeRepository.findAll();
		for (int i = foodTypes.size() - 1; i >= 0; i--) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(foodTypes.get(i).getFoodTypeId()))
					.withObject(foodTypes.get(i)).build();
			String documentId = elasticsearchOperations.index(indexQuery);
			log.info("documentId: " + documentId);
		}
	}

}
