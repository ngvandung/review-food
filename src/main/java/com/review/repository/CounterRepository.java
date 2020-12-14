/**
 * 
 */
package com.review.repository;

import com.review.model.Counter;

/**
 * @author ddung
 *
 */
public interface CounterRepository {
	public Counter createCounter(Counter counter);
	public Counter updateCounter(Counter counter);
	public Counter findById(String clazz);
}
