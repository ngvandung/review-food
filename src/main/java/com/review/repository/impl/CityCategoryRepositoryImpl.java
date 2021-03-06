/**
 * 
 */

package com.review.repository.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
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

import com.review.constant.CityCategoryConstant;
import com.review.model.CityCategory;
import com.review.repository.CityCategoryRepository;
import com.review.repository.elasticsearch.CityCategoryElasticsearchRepository;
import com.review.util.HibernateUtil;

/**
 * @author ddung
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class CityCategoryRepositoryImpl implements CityCategoryRepository {

	private static final Logger log = Logger.getLogger(CityCategoryRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CityCategoryElasticsearchRepository cityCategoryElasticsearchRepository;

	@Override
	public CityCategory findById(long cityId) {
		CityCategory cityCategory = null;
		try {
			Optional<CityCategory> optionalCityCategory = cityCategoryElasticsearchRepository.findById(cityId);
			if (optionalCityCategory.isPresent()) {
				cityCategory = optionalCityCategory.get();
			} else {
				Session session = sessionFactory.openSession();
				Transaction transaction = null;
				transaction = session.beginTransaction();
				cityCategory = session.get(CityCategory.class, cityId);
				transaction.commit();
				session.close();
			}
			return cityCategory;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public CityCategory updateCityCategory(CityCategory cityCategory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.update(cityCategory);
		transaction.commit();
		session.close();
		return cityCategory;
	}

	@Override
	public CityCategory createCityCategory(CityCategory cityCategory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.save(cityCategory);
		transaction.commit();
		session.close();
		return cityCategory;
	}

	@Override
	public CityCategory deleteCityCategory(long cityId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		CityCategory cityCategory = session.get(CityCategory.class, cityId);
		session.delete(cityCategory);
		transaction.commit();
		session.close();
		return cityCategory;
	}

	@Override
	public Iterable<CityCategory> getCityCategories(String cityName, Integer isActive, Long stateId, Integer start,
			Integer end) {
		if (start == null || end == null) {
			start = 0;
			end = 15;
		}
		Pageable sortedByCityId = PageRequest.of(start, end, Sort.by("cityId"));

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(QueryBuilders.matchAllQuery());
		if (cityName != null) {
			QueryBuilder queryBuilder = QueryBuilders.matchQuery(CityCategoryConstant.CITYNAME, cityName)
					.operator(Operator.AND);
			boolQueryBuilder.must(queryBuilder);
		}
		if (isActive != null) {
			QueryBuilder queryBuilder = QueryBuilders.termQuery(CityCategoryConstant.ISACTIVE, isActive);
			boolQueryBuilder.must(queryBuilder);
		}
		if (stateId != null) {
			QueryBuilder queryBuilder = QueryBuilders.termQuery("stateId", stateId);
			boolQueryBuilder.must(queryBuilder);
		}

		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder)
				.withPageable(sortedByCityId).build();

		return cityCategoryElasticsearchRepository.search(searchQuery);
	}

	@Override
	public List<CityCategory> findAll() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		List<CityCategory> cityCategories = HibernateUtil.loadAllData(CityCategory.class, session);
		transaction.commit();
		session.close();
		return cityCategories;
	}

}
