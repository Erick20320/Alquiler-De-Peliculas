package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Pelicula;
import net.javaguides.springboot.repository.PeliculaRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class PeliculaController {

	@Autowired
	private PeliculaRepository peliculaRepository;

	// Obtener todas las peliculas

	@GetMapping("/peliculas")
	public List<Pelicula> getAllPeliculas() {
		return peliculaRepository.findAll();
	}

}
