package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/")
	@ResponseBody
	public String hello(){
		return "Hello world ! first board"
				+ "<a href='/thegarlic1ho/freeboard/init'>link!! </a><br><Br>if this link is broken,  "
				+ "<a href='/garlic1ho/freeboard/init'> come here~</a>";
	}
	
}
