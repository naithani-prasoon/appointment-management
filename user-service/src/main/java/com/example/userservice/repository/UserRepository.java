package com.example.userservice.repository;

import com.example.userservice.web.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.UUID;


public interface UserRepository extends MongoRepository<User, String> {
    //public User findByFirstName(String firstName);
    //public List<User> findByLastName(String lastName);
    User findUserById(String id);

}
