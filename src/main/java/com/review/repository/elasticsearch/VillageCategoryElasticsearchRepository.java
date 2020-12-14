/**
 * 
 */
package com.review.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.review.model.VillageCategory;

/**
 * @author ddung
 *
 */
@Repository
public interface VillageCategoryElasticsearchRepository extends ElasticsearchRepository<VillageCategory, Long> {

}
