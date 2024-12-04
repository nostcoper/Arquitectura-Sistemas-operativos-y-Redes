package com.co.serrato.usuario.repository;

import org.springframework.data.repository.CrudRepository;
import com.co.serrato.common.usuario.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
