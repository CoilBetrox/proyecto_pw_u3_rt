package com.example.demo.uce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.uce.service.IHijoService;
import com.example.demo.uce.service.to.HijoTo;

@RestController
@RequestMapping(value = "/hijos")
public class HijoRestFullController {

	@Autowired
	private IHijoService hijoService;
	
	public List<HijoTo> busccarTodos(){
		return null;
	}
	
	
}
