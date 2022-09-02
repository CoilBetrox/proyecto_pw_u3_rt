package com.example.demo.uce.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.uce.repository.modelo.Empleado;
import com.example.demo.uce.service.IEmpleadoService;
import com.example.demo.uce.service.IHijoService;
import com.example.demo.uce.service.to.EmpleadoTo;
import com.example.demo.uce.service.to.HijoTo;

@RestController
@RequestMapping("APINomina/V1/empleados")
@CrossOrigin("http://localhost:8081/")
public class EmpleadoRestFullController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private IHijoService hijoService;
	
	@PostMapping
	public String crear(@RequestBody Empleado empleado) {
		String msj = "Empleado insertado correctamente";
		try {
			this.empleadoService.crear(empleado);
		} catch (Exception e) {
			msj = "Error intente más tarde"+e;
		}
		return msj;
	}
	
	@PutMapping
	public String actualizar(@RequestBody Empleado empleado){
		String msj = "Empleado actualizado correctamente";
		try {
			this.empleadoService.actualizar(empleado);
		} catch (Exception e) {
			msj = "Error al actualizar";
		}
		return msj;
	}
	
	@GetMapping(path = "/{idEmpleado}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Empleado> buscarEmpleado(@PathVariable("idEmpleado") Integer id) {
		Empleado empl = this.empleadoService.buscarPorId(id);
		return ResponseEntity.ok(empl);
	}
	
	@DeleteMapping(path = "/{idEmpleado}")
	public String eliminar(@PathVariable("idEmpleado") Integer id) {
		String msj = "Eliminado correctamente";
		try {
			this.empleadoService.eliminar(id);
		} catch (Exception e) {
			msj = "Error al eliminar";
		}
		return msj;
	}
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmpleadoTo> buscarTodos() {
		List<EmpleadoTo> lista = this.empleadoService.buscarTodos();
		
		for(EmpleadoTo empl: lista) {
			Link myLink = linkTo(methodOn(EmpleadoRestFullController.class)
					.buscarHijos(empl.getId())).withRel("hijos");
			empl.add(myLink);
		}
		return lista;
	}
	
	
	@GetMapping(path = "/{idEmpleado}/hijos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HijoTo> buscarHijos(@PathVariable("idEmpleado") Integer idEmpleado){
		return this.hijoService.buscarHijosEmpleado(idEmpleado);
		
	}
	
}
