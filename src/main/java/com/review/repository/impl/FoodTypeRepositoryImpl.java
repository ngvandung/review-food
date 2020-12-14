/**
 * 
 */
package com.review.repository.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.review.model.FoodType;
import com.review.repository.FoodTypeRepository;
import com.review.repository.elasticsearch.FoodTypeElasticsearchRepository;
import com.review.util.HibernateUtil;

/**
 * @author ddung
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class FoodTypeRepositoryImpl implements FoodTypeRepository {
	private static final Logger log = Logger.getLogger(FoodTypeRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private FoodTypeElasticsearchRepository foodTypeElasticsearchRepository;

	@Override
	public FoodType findById(long foodTypeId) {
		FoodType foodType = null;
		try {
			Optional<FoodType> optionalFoodType = foodTypeElasticsearchRepository.findById(foodTypeId);
			if (optionalFoodType.isPresent()) {
				foodType = optionalFoodType.get();
			} else {
				Session session = sessionFactory.openSession();
				Transaction transaction = null;
				transaction = session.beginTransaction();
				foodType = session.get(FoodType.class, foodTypeId);
				transaction.commit();
				session.close();
			}
			return foodType;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public FoodType updateFoodType(FoodType foodType) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.update(foodType);
		transaction.commit();
		session.close();
		return foodType;
	}

	@Override
	public FoodType createFoodType(FoodType foodType) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.save(foodType);
		transaction.commit();
		session.close();
		return foodType;
	}

	@Override
	public FoodType deleteFoodType(long foodTypeId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		FoodType foodType = session.get(FoodType.class, foodTypeId);
		session.delete(foodType);
		transaction.commit();
		session.close();
		return foodType;
	}

	@Override
	public Iterable<FoodType> getFoodTypes(String typeName, Integer start, Integer end) {
		if (start == null || end == null) {
			start = 0;
			end = 15;
		}
		Pageable sortedByFoodTypeId = PageRequest.of(start, end, Sort.by("foodTypeId"));

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(QueryBuilders.matchAllQuery());
		if (typeName != null) {
			QueryBuilder queryBuilder = QueryBuilders.matchQuery("typeName", typeName);
			boolQueryBuilder.must(queryBuilder);
		}

		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder)
				.withPageable(sortedByFoodTypeId).build();

		return foodTypeElasticsearchRepository.search(searchQuery);
	}

	@Override
	public List<FoodType> findAll() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		List<FoodType> foodTypes = HibernateUtil.loadAllData(FoodType.class, session);
		transaction.commit();
		session.close();
		return foodTypes;
	}
}
