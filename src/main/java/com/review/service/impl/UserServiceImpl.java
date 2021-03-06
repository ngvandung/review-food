/**
 * 
 */
package com.review.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import com.review.model.User;
import com.review.repository.UserRepository;
import com.review.service.CounterService;
import com.review.service.UserService;

/**
 * @author ddung
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	@Resource
	private ElasticsearchOperations elasticsearchOperations;
	@Autowired
	private CounterService counterService;

	@Override
	public Iterable<User> getUsers(String username, String email, String phone, String firstName, String lastName,
			Integer age, Integer isHost, Integer isEnabled, Integer start, Integer end) {
		return userRepository.getUsers(username, email, phone, firstName, lastName, age, isHost, isEnabled, start, end);
	}

	@Override
	public User updateUser(long userId, String username, String password, String email, String phone, String firstName,
			String lastName, int age, String address, Date birthDay, String description, Integer isHost, Integer sex,
			int isEnabled, String hashSecret, String tmnCode) {
		User user = userRepository.findByUserId(userId);

		if (user != null) {
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setPhone(phone);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setAge(age);
			user.setAddress(address);
			user.setBirthDay(birthDay);
			user.setDescription(description);
			user.setIsHost(isHost);
			user.setIsEnabled(isEnabled);
			user.setModifiedDate(new Date());
			user.setHashSecret(hashSecret);
			user.setTmnCode(tmnCode);
			user.setSex(sex);

			user = userRepository.updateUser(user);
			if (user != null) {
				IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(user.getUserId()))
						.withObject(user).build();
				String documentId = elasticsearchOperations.index(indexQuery);
				log.info("documentId: " + documentId);
			}
		}

		return user;
	}

	@Override
	public User deleteUser(long userId) {
		User user = userRepository.deleteUser(userId);
		if (user != null) {
			String documentId = elasticsearchOperations.delete(User.class, String.valueOf(userId));
			log.info("documentId: " + documentId);
		}
		return user;
	}

	@Override
	public User createUser(String username, String password, String email, String phone, String firstName,
			String lastName, int age, String address, Date birthDay, String description, Integer isHost, Integer sex,
			String avatar, int isEnabled) {
		User user = new User();

		long userId = counterService.increment(User.class.getName());

		user.setUserId(userId);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(age);
		user.setAddress(address);
		user.setBirthDay(birthDay);
		user.setDescription(description);
		user.setIsHost(isHost);
		user.setIsEnabled(isEnabled);
		user.setModifiedDate(new Date());
		user.setCreateDate(new Date());
		user.setAvatar(avatar);
		user.setSex(sex);

		user = userRepository.createUser(user);
		if (user != null) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(user.getUserId())).withObject(user)
					.build();
			String documentId = elasticsearchOperations.index(indexQuery);
			log.info("documentId: " + documentId);
		}

		return user;
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	@Override
	public User findByUserId(long userId) {
		try {
			return userRepository.findByUserId(userId);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public List<User> getUsersByUserRole(String username, String email, String phone, String firstName, String lastName,
			Integer age, Integer isHost, Integer isEnabled, Integer roleId, Integer start, Integer end) {
		return userRepository.getUsersByUserRole(username, email, phone, firstName, lastName, age, isHost, isEnabled,
				roleId, start, end);
	}

	@Override
	public User updateUser(User user) {
		user = userRepository.updateUser(user);
		if (user != null) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(user.getUserId())).withObject(user)
					.build();
			String documentId = elasticsearchOperations.index(indexQuery);
			log.info("documentId: " + documentId);
		}

		return user;
	}

	@Override
	public User uploadAvatar(long userId, String avatar) {
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			user.setAvatar(avatar);
			user = userRepository.updateUser(user);
			if (user != null) {
				IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(user.getUserId()))
						.withObject(user).build();
				String documentId = elasticsearchOperations.index(indexQuery);
				log.info("documentId: " + documentId);
			}
		}
		return user;
	}

}
