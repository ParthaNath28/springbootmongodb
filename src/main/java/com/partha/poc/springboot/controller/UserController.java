package com.partha.poc.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partha.poc.springboot.document.User;
import com.partha.poc.springboot.repository.UserRepository;

@RestController
@RequestMapping("/rest/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<List<User>> getUserList(){
		List<User> users = userRepository.findAll();
		if(!CollectionUtils.isEmpty(users)) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> findByUserName(@PathVariable("id") final Integer id){
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Could not find user",(HttpStatus.NOT_FOUND));
		}
	}
	
	
	@PostMapping(value = "/addUser")
	public ResponseEntity<String> addUser(@RequestBody List<User> userList){
		if(null!=userRepository.saveAll(userList)) {
			return new ResponseEntity<String>("User added successfully", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Problem adding user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
