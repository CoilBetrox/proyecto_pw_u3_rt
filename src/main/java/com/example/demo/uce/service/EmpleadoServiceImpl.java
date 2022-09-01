package com.example.demo.uce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.uce.repository.IEmpleadoRepository;
import com.example.demo.uce.repository.modelo.Empleado;
import com.example.demo.uce.service.to.EmpleadoTo;

import net.bytebuddy.asm.Advice.This;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoRepository empleadoRepository;
	
	@Override
	public void crear(Empleado empledo) {
		// TODO Auto-generated method stub
		this.empleadoRepository.crear(empledo);
	}

	@Override
	public void actualizar(Empleado empleado) {
		// TODO Auto-generated method stub
		this.empleadoRepository.actualizar(empleado);
	}

	@Override
	public Empleado buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.empleadoRepository.buscar(id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.empleadoRepository.eliminar(id);
	}

	@Override
	public List<EmpleadoTo> buscarTodos() {
		// TODO Auto-generated method stub
		List<Empleado> lista = this.empleadoRepository.buscaTodos();
		List<EmpleadoTo> listaFinal = lista.stream().map(empleado -> this.convertir(empleado)).collect(Collectors.toList());
		return listaFinal;
	}
	
	private EmpleadoTo convertir(Empleado empleado){
		EmpleadoTo empl = new EmpleadoTo();
		empl.setId(empleado.getId());
		empl.setNombre(empleado.getNombre());
		empl.setApellido(empleado.getApellido());
		empl.setFechaNacimiento(empleado.getFechaNacimiento());
		return empl;
	}

}
