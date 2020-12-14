/**
 * 
 */
package com.review.repository;

import com.review.model.User;

/**
 * @author ddung
 *
 */
public interface UserDetailsRepository {
	User findUserByUsername(String username);
}
