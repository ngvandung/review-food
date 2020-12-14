/**
 * 
 */
package com.review.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.review.business.VotingBusiness;
import com.review.exception.ForbiddenException;
import com.review.model.Voting;
import com.review.security.PermissionCheckerFactoryUtil;
import com.review.service.VotingService;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public class VotingBusinessImpl implements VotingBusiness {

	@Autowired
	private VotingService votingService;

	@Override
	public Voting findById(long votingId) {
		return votingService.findById(votingId);
	}

	@Override
	public List<Voting> findByClassName_ClassPK(String className, long classPK) {
		return votingService.findByClassName_ClassPK(className, classPK);
	}

	@Override
	public Voting updateVoting(long votingId, int star, long classPK, String className, UserContext userContext) {
		Voting voting = findById(votingId);
		if (PermissionCheckerFactoryUtil.isOwner(userContext, voting.getUserId())) {
			voting = votingService.updateVoting(votingId, star, classPK, className, userContext.getUser().getUserId());
		} else {
			throw new ForbiddenException();
		}
		return voting;
	}

	@Override
	public Voting createVoting(int star, long classPK, String className, UserContext userContext) {
		PermissionCheckerFactoryUtil.checkAuthentication(userContext);

		long userId = userContext.getUser().getUserId();
		return votingService.createVoting(star, classPK, className, userId);

	}

	@Override
	public Voting deleteVoting(long votingId, UserContext userContext) {
		Voting voting = findById(votingId);
		if (PermissionCheckerFactoryUtil.isOwner(userContext, voting.getUserId())) {
			voting = votingService.deleteVoting(votingId);
		} else {
			throw new ForbiddenException();
		}
		return voting;
	}

}
