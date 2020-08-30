package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringController {
	
	@RequestMapping("/test")
	public String hello() {
		return "Hello Spring!";
	}
}
