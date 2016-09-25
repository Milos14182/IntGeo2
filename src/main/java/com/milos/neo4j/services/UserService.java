package com.milos.neo4j.services;

import com.milos.neo4j.data.UserData;

public interface UserService {
	public UserData save(UserData userData);
	public UserData getUser(String username);
	public UserData updateUser(UserData userData);
}
