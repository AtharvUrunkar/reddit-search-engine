package com.atharv.reddit_search_engine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController //Api controler kind of load balancer

@RequestMapping // base path

public class HealthController {
  @GetMapping ("/health")
	public Map<String,String> health(){
	  return Map.of("status","UP");
	}

}
