package com.co.serrato.curso.service;

import com.co.serrato.commons.service.CommonServiceInterface;
import com.co.serrato.curso.models.entity.Curso;


public interface CursoServiceInterface extends CommonServiceInterface<Curso>{
	public Curso save(Curso alumno);
	public void deleteById(Long id);
}
