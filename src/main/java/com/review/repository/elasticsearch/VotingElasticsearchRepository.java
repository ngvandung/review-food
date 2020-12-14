/**
 * 
 */
package com.review.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.review.model.Voting;

/**
 * @author ddung
 *
 */
@Repository
public interface VotingElasticsearchRepository extends ElasticsearchRepository<Voting, Long> {

}
