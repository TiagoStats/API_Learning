package com.API_Spring.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API_Spring.demo.models.Cursos;
import com.API_Spring.demo.repository.CursoRepository;



@RestController
@RequestMapping(value="/api")
public class CursosResource {
	
	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping("/cursos")
	public List<Cursos> listaCursos(){
		return cursoRepository.findAll();
	}

}
