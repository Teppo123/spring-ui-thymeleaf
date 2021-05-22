package com.example.springuithymeleaf.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.example.springuithymeleaf.dto.UserDTO;

@Controller
public class UserViewController {

	@Value("${url.getUsers}")
	private String urlGetUsers;

	@Value("${url.saveUser}")
	private String urlSaveUser;

	@Value("${url.deleteUser}")
	private String urlDeleteUser;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping({ "", "/index" })
	public String showUserList(Model model) {
		model.addAttribute("users", Arrays.asList(this.restTemplate.getForObject(this.urlGetUsers, UserDTO[].class)));
		return "index";
	}

	@PostMapping("/saveUser")
	public String saveUser(@RequestBody UserDTO user) {
		this.restTemplate.postForObject(this.urlSaveUser, user, UserDTO.class);
		return "redirect:/index";
	}

	@PostMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(name = "id") long id) {
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.urlDeleteUser, id, String.class);
		Optional<HttpStatus> status = Optional.ofNullable(response).map(ResponseEntity<String>::getStatusCode);
		if (!status.isPresent()) {
			throw new IllegalArgumentException(response.getBody());
		}
		return "redirect:/index";
	}

}
