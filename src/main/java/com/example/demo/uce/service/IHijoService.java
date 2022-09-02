package com.example.demo.uce.service;

import java.util.List;

import com.example.demo.uce.repository.modelo.Hijo;
import com.example.demo.uce.service.to.HijoTo;

public interface IHijoService {
	
	public List<HijoTo> buscarHijosEmpleado(Integer id);

}
