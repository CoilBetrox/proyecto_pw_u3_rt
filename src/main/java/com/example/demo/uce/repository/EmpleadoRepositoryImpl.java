package com.example.demo.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.uce.repository.modelo.Empleado;
import com.example.demo.uce.repository.modelo.Hijo;

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
		
		return this.entityManager.find(Empleado.class, id);
	}
	
	@Override
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscar(id));
	}



	@Override
	public List<Empleado> buscaTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Empleado> myQuery = this.entityManager
				.createQuery("SELECT e FROM Empleado e", Empleado.class);
		return myQuery.getResultList();
	}

	
	
}
