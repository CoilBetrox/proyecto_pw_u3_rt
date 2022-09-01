package com.example.demo.uce.service;

import java.util.List;

import com.example.demo.uce.repository.modelo.Empleado;
import com.example.demo.uce.service.to.EmpleadoTo;

public interface IEmpleadoService {

	public void crear(Empleado empledo);
	public void actualizar(Empleado empleado);
	public Empleado buscarPorId(Integer id);
	public List<EmpleadoTo> buscarTodos();
	
	public void eliminar (Integer id);
}
