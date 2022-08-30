package com.example.demo.uce.controller;

import java.math.BigDecimal;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.uce.repository.modelo.Empleado;
import com.example.demo.uce.service.IEmpleadoService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoRestFullController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public String crear(@RequestBody Empleado empleado) {
		String msj = "Empleado insertado correctamente";
		try {
			this.empleadoService.crear(empleado);
		} catch (Exception e) {
			msj = "Error intente más tarde"+e;
			throw new RuntimeException();
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
	
	@GetMapping(path = "/{idEmpleado}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Empleado> buscarEmpleado(@PathVariable("idEmpleado") Integer id) {
		Empleado empl = this.empleadoService.buscarPorId(id);
		return ResponseEntity.ok(empl);
	}
	
	@GetMapping(path = "/status/{idEmpleado}")
	public ResponseEntity<Empleado> buscarEmpleadoStatus(@PathVariable("idEmpleado") Integer id) {
		Empleado empl = this.empleadoService.buscarPorId(id);
		//return ResponseEntity.ok(empl);
		return ResponseEntity.status(HttpStatus.OK).body(empl); 
	}
	
	//envia mensajes en headers
	@GetMapping(path = "/header/{idEmpleado}")
	public ResponseEntity<Empleado> buscarEmpleadoHeader(@PathVariable("idEmpleado") Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("detalleMensaje", "esta bien pero falta un mensaje");
		headers.add("solicitud", "Recuerda consumirme maniana");
		headers.add("valor", "1");
		
		Empleado empl = this.empleadoService.buscarPorId(id);
		//return ResponseEntity.ok(empl);
		return new ResponseEntity<>(empl, headers, HttpStatus.OK); 
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
	
	@GetMapping
	public List<Empleado> buscarEmpleadoPorSalario(@RequestParam(value="salario") BigDecimal salario, @RequestParam(value ="provincia") String provincia) {
		List<Empleado> datos = this.empleadoService.buscaPorSalario(salario);
		return datos;
	}
	
	
	//este metodo sirve de referencia de nomenclatura incorrecta 
	/*
	@GetMapping(path = "/adicional")
	public ResponseEntity<List<Empleado>> buscarEmpleadoPorSalario1(@RequestParam(value="sal") BigDecimal salario) {
		List<Empleado> datos = this.empleadoService.buscaPorSalario(salario);
		return ResponseEntity.ok(datos);
	}
	*/
	
	
	
}
