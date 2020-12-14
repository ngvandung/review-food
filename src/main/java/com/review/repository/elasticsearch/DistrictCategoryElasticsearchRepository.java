/**
 * 
 */
package com.review.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.review.model.DistrictCategory;

/**
 * @author ddung
 *
 */
@Repository
public interface DistrictCategoryElasticsearchRepository extends ElasticsearchRepository<DistrictCategory, Long> {

}
