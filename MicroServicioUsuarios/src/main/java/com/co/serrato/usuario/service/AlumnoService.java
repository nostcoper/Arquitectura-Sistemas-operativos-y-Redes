package com.co.serrato.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.co.serrato.common.usuario.entity.Alumno;
import com.co.serrato.commons.service.CommonService;
import com.co.serrato.usuario.repository.AlumnoRepository;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlumnoService extends CommonService<Alumno> {

    private final AlumnoRepository dao;

    @Autowired
    public AlumnoService(CrudRepository<Alumno, Long> crudRepository, MeterRegistry meterRegistry, AlumnoRepository alumnoRepository) {
        super(crudRepository);
        this.dao = alumnoRepository;

        // Registro de la métrica dinámica al inicializar el servicio
        meterRegistry.gauge("alumnos.total", this, AlumnoService::countAlumnos);
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {
        return dao.save(alumno); // Las métricas se actualizan automáticamente
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id); // Las métricas se actualizan automáticamente
    }

    @Transactional(readOnly = true)
    public long countAlumnos() {
        return dao.count();
    }
}
