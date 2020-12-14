/**
 * 
 */
package com.review.business.util;

import java.util.List;

import com.review.business.VotingBusiness;
import com.review.model.Voting;
import com.review.util.BeanUtil;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public class VotingBusinessFactoryUtil {
	// Design pattern - Singleton
	private static VotingBusiness _votingBusiness;

	public static VotingBusiness getVotingBusiness() {

		if (_votingBusiness == null) {
			_votingBusiness = BeanUtil.getBean(VotingBusiness.class);
		}
		return _votingBusiness;
	} // ============================

	public static Voting findById(long votingId) {
		return getVotingBusiness().findById(votingId);
	}

	public static List<Voting> findByClassName_ClassPK(String className, long classPK) {
		return getVotingBusiness().findByClassName_ClassPK(className, classPK);
	}

	public static Voting updateVoting(long votingId, int star, long classPK, String className,
			UserContext userContext) {
		return getVotingBusiness().updateVoting(votingId, star, classPK, className, userContext);
	}

	public static Voting createVoting(int star, long classPK, String className, UserContext userContext) {
		return getVotingBusiness().createVoting(star, classPK, className, userContext);
	}

	public static Voting deleteVoting(long votingId, UserContext userContext) {
		return getVotingBusiness().deleteVoting(votingId, userContext);
	}
}
