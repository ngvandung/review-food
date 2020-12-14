/**
 * 
 */
package com.review.business;

import com.review.model.User;

/**
 * @author ddung
 *
 */
public interface AuthBusiness {
	public User login(String username, String password);
}
