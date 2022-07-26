package com.example.userservice.repository;

import com.example.userservice.web.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();
    User findUserById(String id);

    List<User> findUsersByFirstNameAndLastName(String firstName, String lastName);
    List<User> findUsersByFirstName(String firstName);
    List<User> findUsersByLastName(String lastName);

}
