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

    private final MeterRegistry meterRegistry;

    @Autowired
    public AlumnoService(CrudRepository<Alumno, Long> dao, MeterRegistry meterRegistry) {
        super(dao);
        this.meterRegistry = meterRegistry;
    }

    @Autowired
    private AlumnoRepository dao;

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {
        Alumno savedAlumno = dao.save(alumno);
        // Incrementar el contador de alumnos guardados
        meterRegistry.counter("alumno.save.count").increment();
        return savedAlumno;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
        // Incrementar el contador de alumnos eliminados
        meterRegistry.counter("alumno.delete.count").increment();
    }
}
