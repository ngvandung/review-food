/**
 * 
 */
package com.review.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.review.model.Food;

/**
 * @author ddung
 *
 */
@Repository
public interface FoodElasticsearchRepository extends ElasticsearchRepository<Food, Long> {

}
