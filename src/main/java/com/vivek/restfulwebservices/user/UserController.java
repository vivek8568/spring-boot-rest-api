package com.vivek.restfulwebservices.user;

import java.net.URI;


import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vivek.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserController {
	
	@Autowired
	private UserDAOService userService;
	
	@GetMapping("/users")
	public ArrayList<User> getAllUsers() {
		return userService.getAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id) {
		User user = userService.getById(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
//		EntityModel<User> model = new EntityModel<User>(user);
//		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
//		
//		model.add(linkTo.withRel("all-users"));
//		
//		return model;
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.createUser(user);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
	
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		User deletedUser = userService.deleteUserById(id);
		if(deletedUser == null) {
			throw new UserNotFoundException("id-"+id);
		}
	}
}
