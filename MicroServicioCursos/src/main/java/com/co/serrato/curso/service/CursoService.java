package com.co.serrato.curso.service;

import com.co.serrato.commons.service.CommonService;
import com.co.serrato.curso.models.entity.Curso;
import com.co.serrato.curso.repository.CursoRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService extends CommonService<Curso> {

    @Autowired
    private CursoRepository dao;

    private final MeterRegistry meterRegistry;

    public CursoService(CrudRepository<Curso, Long> dao, MeterRegistry meterRegistry) {
        super(dao);
        this.meterRegistry = meterRegistry;
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        Curso savedCurso = dao.save(curso);
        updateMetrics();
        return savedCurso;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
        updateMetrics();
    }

    @Transactional(readOnly = true)
    public long countCursos() {
        return dao.count();
    }

    private void updateMetrics() {
        long totalCursos = dao.count();
        meterRegistry.gauge("cursos.total", totalCursos);
    }
}
