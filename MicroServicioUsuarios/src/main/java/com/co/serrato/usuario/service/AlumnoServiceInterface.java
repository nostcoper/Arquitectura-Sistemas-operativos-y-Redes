package com.co.serrato.usuario.service;


import com.co.serrato.common.usuario.entity.Alumno;
import com.co.serrato.commons.service.CommonServiceInterface;

public interface AlumnoServiceInterface extends CommonServiceInterface<Alumno>{
	public Alumno save(Alumno alumno);
	public void deleteById(Long id);
}
