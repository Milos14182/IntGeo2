package com.milos.neo4j.converter;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.milos.neo4j.data.UserData;
import com.milos.neo4j.domain.nodes.City;
import com.milos.neo4j.domain.nodes.User;
import com.milos.neo4j.intrestinggeography.UserDataMultipart;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class UserConverter extends AbstractConverter<User, UserData> {

    @Autowired
    private CityConverter cityConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void copyFromEntityToData(User entity, UserData data) {
        data.setUserImage(new UserDataMultipart(entity.getUserImage()));
        data.setBase64Image(StringUtils.newStringUtf8(Base64.encodeBase64(entity.getUserImage(), false)));
        super.copyFromEntityToData(entity, data, "image", "base64Image");
        if (entity.getCity() != null) {
            cityConverter.copyFromEntityToData(entity.getCity(), data.getCity());
        }
    }

    @Override
    public void copyFromDataToEntity(UserData data, User entity) {
        if (data.getUserImage() == null || data.getUserImage().getSize() == 0) {
            entity.setUserImage(null);
        } else {
            try {
                byte[] blob = data.getUserImage().getBytes();
                entity.setUserImage(blob);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String pass = passwordEncoder.encode(data.getPassword());
        data.setPassword(pass);

        super.copyFromDataToEntity(data, entity, "image");
        entity.setCity(new City());
        if (data.getCity() != null) {
            cityConverter.copyFromDataToEntity(data.getCity(), entity.getCity());
        }
    }

}
