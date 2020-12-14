/**
 * 
 */
package com.review.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.review.model.StateCategory;

/**
 * @author ddung
 *
 */
@Repository
public interface StateCategoryElasticsearchRepository extends ElasticsearchRepository<StateCategory, Long> {

}
