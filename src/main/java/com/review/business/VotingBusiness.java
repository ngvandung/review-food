/**
 * 
 */
package com.review.business;

import java.util.List;

import com.review.model.Voting;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public interface VotingBusiness {
	public Voting findById(long votingId);

	public List<Voting> findByClassName_ClassPK(String className, long classPK);

	public Voting updateVoting(long votingId, int star, long classPK, String className, UserContext userContext);

	public Voting createVoting(int star, long classPK, String className, UserContext userContext);

	public Voting deleteVoting(long votingId, UserContext userContext);
}
