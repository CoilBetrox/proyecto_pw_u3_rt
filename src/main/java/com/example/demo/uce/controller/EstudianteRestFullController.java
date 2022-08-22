package com.example.demo.uce.controller;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.uce.repository.modelo.Empleado;
import com.example.demo.uce.repository.modelo.Estudiante;
import com.example.demo.uce.service.IEstudianteService;

@RestController
@RequestMapping("APINomina/V1/estudiantes")
public class EstudianteRestFullController {
	
	@Autowired
	private IEstudianteService estudianteService;
	
	@PostMapping
	public String crear(@RequestBody Estudiante estudiante) {
		String msj = "Estudiante insertado correctamente";
		try {
			this.estudianteService.crear(estudiante);
		} catch (Exception e) {
			msj = "Error intente más tarde"+e;
		}
		return msj;
	}
	
	@PutMapping
	public String actualizar(@RequestBody Estudiante estudiante){
		String msj = "Estudiante actualizado correctamente";
		try {
			this.estudianteService.actualizar(estudiante);
		} catch (Exception e) {
			msj = "Error al actualizar" +e;
		}
		return msj;
	}
	
	@GetMapping(path = "/{idEstudiante}")
	public ResponseEntity<Estudiante> buscaEstudiante(@PathVariable("idEstudiante") Integer id){
		Estudiante estu = this.estudianteService.buscarPorId(id);
		return ResponseEntity.ok(estu);
	}
	
	@DeleteMapping(path = "/{idEstudiante}")
	public String eliminarEstudiante(@PathVariable("idEstudiante") Integer id){
		String msj = "Eliminado correctamente";
		try {
			this.estudianteService.eliminar(id);
		} catch (Exception e) {
			msj = "Error al eliminar" + e;
		}
		return msj;
	}
}
