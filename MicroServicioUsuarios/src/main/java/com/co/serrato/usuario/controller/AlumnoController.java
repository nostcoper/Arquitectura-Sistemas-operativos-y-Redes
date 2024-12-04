package com.co.serrato.usuario.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.serrato.common.usuario.entity.Alumno;
import com.co.serrato.commons.controller.CommonController;
import com.co.serrato.usuario.service.AlumnoService;

import io.micrometer.core.instrument.MeterRegistry;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    private final MeterRegistry meterRegistry;

    public AlumnoController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> update(@PathVariable Long id, @RequestBody Alumno alumno) {
        Optional<Alumno> existingAlumno = service.findById(id);
        if (existingAlumno.isPresent()) {
            Alumno alumnoBd = existingAlumno.get();
            alumnoBd.setNombre(alumno.getNombre());
            alumnoBd.setApellido(alumno.getApellido());
            alumnoBd.setEmail(alumno.getEmail());
            service.save(alumnoBd);
            return ResponseEntity.ok(alumnoBd);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/metrics/total")
    public ResponseEntity<Long> getTotalAlumnos() {
        long totalAlumnos = StreamSupport.stream(service.findAll().spliterator(), false).count();
        meterRegistry.gauge("alumnos.total", totalAlumnos);
        return ResponseEntity.ok(totalAlumnos);
    }
}
