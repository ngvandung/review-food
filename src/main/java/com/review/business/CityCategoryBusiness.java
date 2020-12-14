/**
 * 
 */
package com.review.business;

import com.review.model.CityCategory;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public interface CityCategoryBusiness {
	public Iterable<CityCategory> getCityCategories(String cityName, Integer isActive, Long stateId, Integer start,
			Integer end);

	public CityCategory updateCityCategory(long cityId, String cityName, Integer isActive, long stateId, UserContext userContext);

	public CityCategory createCityCategory(String cityName, long stateId, UserContext userContext);

	public CityCategory deleteCityCategory(long cityId, UserContext userContext);

	public CityCategory findById(long cityId);
	
	public void indexing(UserContext userContext);
}
