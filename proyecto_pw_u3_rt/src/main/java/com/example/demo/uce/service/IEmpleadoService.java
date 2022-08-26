package com.example.demo.uce.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.uce.repository.modelo.Empleado;

public interface IEmpleadoService {

	public void crear(Empleado empledo);
	public void actualizar(Empleado empleado);
	public Empleado buscarPorId(Integer id);
	public List<Empleado> buscaPorSalario(BigDecimal salario);
	public void eliminar (Integer id);
}
