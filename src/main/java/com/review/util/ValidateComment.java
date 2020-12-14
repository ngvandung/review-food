/**
 * 
 */
package com.review.util;

/**
 * @author ddung
 *
 */
public class ValidateComment {
	public static boolean validateComment(String content) {
		if (content.contains("cá hồi")) {
			return false;
		}
		return true;
	}
}
