package com.example.demo.uce.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
		
		return this.entityManager.find(Empleado.class, id);
	}
	
	@Override
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscar(id));
	}

	//si se requiere pasar mas parametros, se realiza desde aquí
	@Override
	public List<Empleado> buscaPorSalario(BigDecimal salario) {
		// TODO Auto-generated method stub
		TypedQuery<Empleado> myQuery = this.entityManager.createQuery("SELECT e FROM Empleado e WHERE e.salario > :salarioDato", Empleado.class);
		myQuery.setParameter("salarioDato", salario);
		return myQuery.getResultList();
	}

	
	
}
