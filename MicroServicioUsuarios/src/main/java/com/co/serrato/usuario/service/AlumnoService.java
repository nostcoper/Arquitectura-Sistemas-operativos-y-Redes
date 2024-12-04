package com.co.serrato.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.co.serrato.common.usuario.entity.Alumno;
import com.co.serrato.commons.service.CommonService;
import com.co.serrato.usuario.repository.AlumnoRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AlumnoService extends CommonService<Alumno> {

    public AlumnoService(CrudRepository<Alumno, Long> dao) {
		super(dao);
	}

	@Autowired 
    private AlumnoRepository dao;

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {
        return dao.save(alumno);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
