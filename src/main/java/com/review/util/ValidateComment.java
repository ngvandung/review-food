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
		if ("đcm, súc vật, súc sinh, ngu".contains(content)) {
			return false;
		}
		return true;
	}
}
