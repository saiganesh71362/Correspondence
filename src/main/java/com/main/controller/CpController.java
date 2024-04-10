package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.binding.CpResponse;
import com.main.service.CpService;

@RestController
public class CpController {
	@Autowired
	private CpService cpService;
	
	@GetMapping("/process")
	public CpResponse processTriggers() {
		 return cpService.processPendingTriggers();
	}


}
