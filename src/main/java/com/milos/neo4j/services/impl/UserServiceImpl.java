package com.milos.neo4j.services.impl;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.UserConverter;
import com.milos.neo4j.data.CityData;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.domain.nodes.City;
import com.milos.neo4j.domain.nodes.User;
import com.milos.neo4j.domain.relations.LivesInCity;
import com.milos.neo4j.repository.CityRepository;
import com.milos.neo4j.repository.UserRepository;
import com.milos.neo4j.repository.relations.LivesInCityRepository;
import com.milos.neo4j.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LivesInCityRepository livesInCityRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private UserConverter userConverter;

	public UserData save(UserData userData) {
		City city = cityRepository.getCityByName(userData.getCity().getName());
		userData.setCity(new CityData());
		User user = new User();
		userConverter.copyFromDataToEntity(userData, user);
		user.setCity(city);
		userRepository.save(user);
		userConverter.copyFromEntityToData(user, userData);
		return userData;
	}

	public UserData getUser(String username) {
		LivesInCity livesInCity = livesInCityRepository.getUserByUsername(username);
		UserData userData = null;
		if (livesInCity != null) {
			User user = livesInCity.getUser();
			user.setCity(livesInCity.getCity());
			userData = new UserData();
			userData.setCity(new CityData());
			userConverter.copyFromEntityToData(user, userData);
		}
		return userData;
	}

	public UserData updateUser(UserData userData) {
		User user = userRepository.getUserByUsername(userData.getUsername());
		User dataUser = new User();
		userConverter.copyFromDataToEntity(userData, dataUser);
		String image = StringUtils.newStringUtf8(Base64.encodeBase64(dataUser.getUserImage(), false));
		if (image == null)
			image = "NULL";
		user = userRepository.updateUser(dataUser.getUsername(), dataUser.getFirstname(), dataUser.getLastname(),
				dataUser.getPassword(), dataUser.getAge(), dataUser.getGender(), image,
				dataUser.getEmail());
		userConverter.copyFromEntityToData(user, userData);
		return userData;
	}
}