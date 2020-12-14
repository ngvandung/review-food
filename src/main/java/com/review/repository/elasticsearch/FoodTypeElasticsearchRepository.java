/**
 * 
 */
package com.review.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.review.model.FoodType;

/**
 * @author ddung
 *
 */
@Repository
public interface FoodTypeElasticsearchRepository extends ElasticsearchRepository<FoodType, Long> {

}
