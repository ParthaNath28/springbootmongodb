package com.partha.poc.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.partha.poc.springboot.document.User;

public interface UserRepository extends MongoRepository<User, Integer> {

}
