/**
 * 
 */

package com.review.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.review.model.CityCategory;

/**
 * @author ddung
 *
 */
@Repository
public interface CityCategoryElasticsearchRepository extends ElasticsearchRepository<CityCategory, Long> {

}
