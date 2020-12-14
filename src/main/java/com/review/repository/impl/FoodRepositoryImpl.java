/**
 * 
 */
package com.review.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.review.model.Food;
import com.review.repository.FoodRepository;
import com.review.repository.elasticsearch.FoodElasticsearchRepository;
import com.review.util.HibernateUtil;
import com.review.util.RentUtil;

/**
 * @author ddung
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class FoodRepositoryImpl implements FoodRepository {
	private static final Logger log = Logger.getLogger(FoodRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private FoodElasticsearchRepository foodElasticsearchRepository;

	@Override
	public Food findById(long foodId) {
		Food food = null;
		try {
			Optional<Food> optionalFood = foodElasticsearchRepository.findById(foodId);
			if (optionalFood.isPresent()) {
				food = optionalFood.get();
			} else {
				Session session = sessionFactory.openSession();
				Transaction transaction = null;
				transaction = session.beginTransaction();
				food = session.get(Food.class, foodId);
				transaction.commit();
				session.close();
			}
			return food;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public Food updateFood(Food food) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.update(food);
		transaction.commit();
		session.close();
		return food;

	}

	@Override
	public Food createFood(Food food) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.save(food);
		transaction.commit();
		session.close();
		return food;
	}

	@Override
	public Food deleteFood(long foodId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		Food food = session.get(Food.class, foodId);
		session.delete(food);
		transaction.commit();
		session.close();
		return food;
	}

	@Override
	public List<Food> findAll() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		List<Food> foods = HibernateUtil.loadAllData(Food.class, session);
		transaction.commit();
		session.close();
		return foods;
	}

	@Override
	public List<Food> findMyFoods(Long ownerFoodId) {
		List<Food> result = new ArrayList<Food>();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			if (session != null) {
				transaction = session.beginTransaction();

				StringBuilder hql = new StringBuilder();
				hql.append("FROM Food H WHERE 1 = 1");
				if (ownerFoodId != null) {
					hql.append(" AND H.ownerFoodId = :ownerFoodId");
				}
				// hql.append(" GROUP BY H.homeId")

				Query query = session.createQuery(hql.toString());

				if (ownerFoodId != null) {
					query.setParameter("ownerFoodId", ownerFoodId);
				}

				result = query.getResultList();

				transaction.commit();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error(e);
		}
		session.close();

		return result;
	}

}
