/**
 * 
 */
package com.review.business;

import java.util.List;

import com.review.model.Comment;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public interface CommentBusiness {
	public Comment findById(long commentId);

	public List<Comment> findByClassName_ClassPK(String className, long classPK);

	public Comment updateComment(long commentId, String content, long classPK, String className,
			UserContext userContext);

	public Comment createComment(String content, long classPK, String className, UserContext userContext);

	public Comment deleteComment(long commentId, UserContext userContext);
}
