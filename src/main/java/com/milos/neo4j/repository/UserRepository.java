package com.milos.neo4j.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.User;

public interface UserRepository extends GraphRepository<User> {

    @Query("MATCH (u:User) WHERE u.username = {0} RETURN u")
    public User getUserByUsername(String username);

    @Query("MATCH (u:User) WHERE u.username = {0} SET u.firstname = {1}, u.lastname = {2}, u.password = {3}, u.age ={4}, u.gender = {5}, u.userImage = {6}, u.email = {7} return u")
    public User updateUser(String username, String firstname, String lastname, String password, String age,
            String gender, String image, String email);
}
