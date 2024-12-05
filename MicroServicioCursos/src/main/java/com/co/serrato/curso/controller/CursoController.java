package com.co.serrato.curso.controller;

import com.co.serrato.common.usuario.entity.Alumno;
import com.co.serrato.commons.controller.CommonController;
import com.co.serrato.curso.models.entity.Curso;
import com.co.serrato.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController extends CommonController<Curso, CursoService> {

	@Value("${config.balanceador.test}")
	private String balanceadorTest;

	@PutMapping("/{id}")
	public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso) {
		Optional<Curso> existingCurso = service.findById(id);
		if (existingCurso.isPresent()) {
			Curso cursoBd = existingCurso.get();
			cursoBd.setNombre(curso.getNombre());
			service.save(cursoBd);
			return ResponseEntity.ok(cursoBd);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/asignar-usuario")
	public ResponseEntity<?> asignarUsuario(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
		Optional<Curso> curso = service.findById(id);
		if (curso.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		Curso cursoBd = curso.get();
		alumnos.forEach(cursoBd::addAlumnos);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoBd));
	}

	@PutMapping("/{id}/eliminar-usuario")
	public ResponseEntity<?> eliminarUsuario(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Curso> curso = service.findById(id);
		if (curso.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		Curso cursoBd = curso.get();
		cursoBd.removeAlumnos(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoBd));
	}

	@GetMapping("/metrics/total")
	public ResponseEntity<Long> getTotalCursos() {
		long totalCursos = service.countCursos();
		return ResponseEntity.ok(totalCursos);
	}
}
