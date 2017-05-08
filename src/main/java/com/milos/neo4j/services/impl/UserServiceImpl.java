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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(propagation = Propagation.MANDATORY)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LivesInCityRepository livesInCityRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
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

    @Override
    @Transactional(readOnly = true)
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

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserData updateUser(UserData userData) {
        User dataUser = new User();
        userConverter.copyFromDataToEntity(userData, dataUser);
        String image = StringUtils.newStringUtf8(Base64.encodeBase64(dataUser.getUserImage(), false));
        if (image == null) {
            image = "NULL";
        }
        User user = userRepository.updateUser(dataUser.getUsername(), dataUser.getFirstname(), dataUser.getLastname(),
                dataUser.getPassword(), dataUser.getAge(), dataUser.getGender(), image,
                dataUser.getEmail());
        userConverter.copyFromEntityToData(user, userData);
        return userData;
    }

    @Override
    @Transactional(readOnly = true)
    public UserData authenticateUser(String username)throws RuntimeException {
        User user = userRepository.getUserByUsername(username);        
        if (user != null) {
            UserData userData = new UserData();
            userData.setCity(new CityData());
            LivesInCity livesInCity = livesInCityRepository.getUserByUsername(username);
            if (livesInCity!=null && livesInCity.getCity()!=null) {
                user.setCity(livesInCity.getCity());
            }
            userConverter.copyFromEntityToData(user, userData);
            return userData;
        }        
        throw new RuntimeException("Authentication for user failed, user is null.");
    }

}
