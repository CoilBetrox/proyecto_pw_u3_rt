package com.example.demo.uce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.uce.repository.IHijoRepository;
import com.example.demo.uce.repository.modelo.Hijo;
import com.example.demo.uce.service.to.HijoTo;

@Service
public class HijoServiceImpl implements IHijoService {

	@Autowired
	private  IHijoRepository hijoRepository;
	
	@Override
	public List<HijoTo> buscarHijosEmpleado(Integer id) {
		// TODO Auto-generated method stub
		List<Hijo> lista = this.hijoRepository.buscarHijosEmpleado(id);
		return lista.stream().map(hijo -> this.convertir(hijo)).collect(Collectors.toList());
	}
	
	private HijoTo convertir(Hijo hijo) {
		HijoTo hijoTo = new HijoTo();
		hijoTo.setId(hijo.getId());
		hijoTo.setNombre(hijo.getNombre());
		hijoTo.setGenero(hijo.getGenero());
		return hijoTo;
		
	}

}
