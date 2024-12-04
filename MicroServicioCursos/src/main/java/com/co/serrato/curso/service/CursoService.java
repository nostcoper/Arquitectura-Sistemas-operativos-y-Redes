package com.co.serrato.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.co.serrato.commons.service.CommonService;
import com.co.serrato.curso.models.entity.Curso;
import com.co.serrato.curso.repository.CursoRepository;

@Service
public class CursoService extends CommonService<Curso>{

	@Autowired 
    private CursoRepository dao;

	
	public CursoService(CrudRepository<Curso, Long> dao) {
		super(dao);
	}
	

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return dao.save(curso);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
