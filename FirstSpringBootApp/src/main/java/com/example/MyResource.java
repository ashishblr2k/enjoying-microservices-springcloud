package com.example;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyResource {
	
	
	@RequestMapping(value="/",produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String getData(){
		return new Date().toString();
	}

}
