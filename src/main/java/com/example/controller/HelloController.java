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
				+ "<a href='/thegarlic1ho/freeboard/init'>링크</a>";
	}
	
}
