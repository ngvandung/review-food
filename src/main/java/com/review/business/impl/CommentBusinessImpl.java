/**
 * 
 */
package com.review.business.impl;

import java.util.List;

import com.review.ai.NaiveBayes;
import com.review.constant.CommonConstant;
import com.review.model.Food;
import com.review.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;

import com.review.business.CommentBusiness;
import com.review.exception.BadRequestException;
import com.review.exception.ForbiddenException;
import com.review.model.Comment;
import com.review.security.PermissionCheckerFactoryUtil;
import com.review.service.CommentService;
import com.review.util.UserContext;
import com.review.util.ValidateComment;

/**
 * @author ddung
 *
 */
public class CommentBusinessImpl implements CommentBusiness {

	@Autowired
	private CommentService commentService;
	@Autowired
	private FoodService foodService;

	private NaiveBayes naiveBayes;

	public CommentBusinessImpl(){
		naiveBayes = new NaiveBayes();
	}

	@Override
	public Comment findById(long commentId) {
		return commentService.findById(commentId);
	}

	@Override
	public Comment updateComment(long commentId, String content, long classPK, String className,
			UserContext userContext) {
		Comment comment = findById(commentId);
		if (PermissionCheckerFactoryUtil.isOwner(userContext, comment.getUserId())) {
			comment = commentService.updateComment(commentId, content, classPK, className,
					userContext.getUser().getUserId());
			if (comment != null) {
				int commentPrediction = naiveBayes.prediction(content);
				if (className.equals(Food.class.getName())) {
					Food food = foodService.findById(classPK);
					if (commentPrediction == CommonConstant.CHE){
						food.setCommentPoint(food.getCommentPoint() - 1);
					} else if (commentPrediction == CommonConstant.KHEN) {
						food.setCommentPoint(food.getCommentPoint() + 1);
					}
					foodService.updateFood(food);
				}
			}
		} else {
			throw new ForbiddenException();
		}
		return comment;
	}

	@Override
	public Comment createComment(String content, long classPK, String className, UserContext userContext) {
		PermissionCheckerFactoryUtil.checkAuthentication(userContext);

		if (!ValidateComment.validateComment(content)) {
			throw new BadRequestException();
		}

		long userId = userContext.getUser().getUserId();
		Comment comment = commentService.createComment(content, classPK, className, userId);
		if (comment != null) {
			int commentPrediction = naiveBayes.prediction(content);
			if (className.equals(Food.class.getName())) {
				Food food = foodService.findById(classPK);
				if (commentPrediction == CommonConstant.CHE){
					food.setCommentPoint(food.getCommentPoint() - 1);
				} else if (commentPrediction == CommonConstant.KHEN) {
					food.setCommentPoint(food.getCommentPoint() + 1);
				}
				foodService.updateFood(food);
			}
		}
		return comment;
	}

	@Override
	public Comment deleteComment(long commentId, UserContext userContext) {
		Comment comment = findById(commentId);
		if (PermissionCheckerFactoryUtil.isOwner(userContext, comment.getUserId())) {
			comment = commentService.deleteComment(commentId);
		} else {
			throw new ForbiddenException();
		}
		return comment;
	}

	@Override
	public List<Comment> findByClassName_ClassPK(String className, long classPK) {
		return commentService.findByClassName_ClassPK(className, classPK);
	}

}
