/**
 * 
 */
package com.review.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.review.model.Comment;

/**
 * @author ddung
 *
 */
@Repository
public interface CommentElasticsearchRepository extends ElasticsearchRepository<Comment, Long> {

}
