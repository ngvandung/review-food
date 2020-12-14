/**
 * 
 */
package com.review.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.review.business.impl.AuthBusinessImpl;
import com.review.business.impl.CityCategoryBusinessImpl;
import com.review.business.impl.CommentBusinessImpl;
import com.review.business.impl.DistrictCategoryBusinessImpl;
import com.review.business.impl.FileEntryBusinessImpl;
import com.review.business.impl.FoodBusinessImpl;
import com.review.business.impl.FoodTypeBusinessImpl;
import com.review.business.impl.StateCategoryBusinessImpl;
import com.review.business.impl.UserBusinessImpl;
import com.review.business.impl.VillageCategoryBusinessImpl;
import com.review.business.impl.VotingBusinessImpl;
//import com.review.scheduler.MessageQueueScheduler;
import com.review.util.ApplicationContext;
import com.review.util.BeanUtil;
import com.review.util.QueryUtil;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.review")
public class AppConfig implements WebMvcConfigurer {
	@Bean
	public ViewResolver viewResolver() {
		System.out.println("======> AppConfig");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/resources/");
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000000);
		return multipartResolver;
	}

	// Create bean
	@Bean
	public UserBusinessImpl userBusinessImpl() {
		return new UserBusinessImpl();
	}

//	@Bean
//	public MessageQueueScheduler messageQueueScheduler() {
//		return new MessageQueueScheduler();
//	}

	@Bean
	public QueryUtil queryUtil() {
		return new QueryUtil();
	}

	@Bean
	public CommentBusinessImpl commentBusinessImpl() {
		return new CommentBusinessImpl();
	}

	@Bean
	public VotingBusinessImpl votingBusinessImpl() {
		return new VotingBusinessImpl();
	}

	@Bean
	public FileEntryBusinessImpl fileEntryBusinessImpl() {
		return new FileEntryBusinessImpl();
	}

	@Bean
	public FoodTypeBusinessImpl houseTypeBusinessImpl() {
		return new FoodTypeBusinessImpl();
	}

	@Bean
	public FoodBusinessImpl houseBusinessImpl() {
		return new FoodBusinessImpl();
	}

	@Bean
	public CityCategoryBusinessImpl cityCategoryBusinessImpl() {
		return new CityCategoryBusinessImpl();
	}

	@Bean
	public DistrictCategoryBusinessImpl districtCategoryBusinessImpl() {
		return new DistrictCategoryBusinessImpl();
	}

	@Bean
	public StateCategoryBusinessImpl stateCategoryBusinessImpl() {
		return new StateCategoryBusinessImpl();
	}

	@Bean
	public VillageCategoryBusinessImpl villageCategoryBusinessImpl() {
		return new VillageCategoryBusinessImpl();
	}

	@Bean
	public AuthBusinessImpl authBusinessImpl() {
		return new AuthBusinessImpl();
	}

	@Bean
	public UserContext userContext() {
		return new UserContext();
	}

	@Bean
	public BeanUtil beanUtil() {
		return new BeanUtil();
	}

	@Bean
	public ApplicationContext applicationContext() {
		return new ApplicationContext();
	}
}
