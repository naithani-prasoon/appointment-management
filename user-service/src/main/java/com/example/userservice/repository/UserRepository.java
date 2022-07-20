package com.example.userservice.repository;

import com.example.userservice.web.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();
    //public List<User> findByLastName(String lastName);
    User findUserById(String id);

    List<User> findUsersByFirstNameAndLastName(String firstName, String lastName);
    List<User> findUsersByFirstName(String firstName);
    List<User> findUsersByLastName(String lastName);

}
