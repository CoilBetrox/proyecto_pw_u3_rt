package com.example.demo.uce.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.uce.repository.modelo.Empleado;

@Repository
@Transactional
public class EmpleadoRepositoryImpl implements IEmpleadoRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void crear(Empleado empleado) {
		this.entityManager.persist(empleado);
	}

	@Override
	public void actualizar(Empleado empleado) {
		this.entityManager.merge(empleado);
	}

	@Override
	public Empleado buscar(Integer id) {
		this.entityManager.find(Empleado.class, id);
		return null;
	}
	
	@Override
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscar(id));
	}

	
	
}
