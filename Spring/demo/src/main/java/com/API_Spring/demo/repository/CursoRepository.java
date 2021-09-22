package com.API_Spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API_Spring.demo.models.Cursos;

public interface CursoRepository extends JpaRepository<Cursos, Long>{

}
