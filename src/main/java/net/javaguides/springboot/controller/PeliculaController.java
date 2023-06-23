package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Pelicula;
import net.javaguides.springboot.repository.PeliculaRepository;

@CrossOrigin(origins = "http://192.168.1.24:3000/")
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

	// Crear Peliculas

	@PostMapping("/peliculas")
	public Pelicula CreatePeliculas(@RequestBody Pelicula pelicula) {
		return peliculaRepository.save(pelicula);
	}

	// Obtener peliculas por id

	@GetMapping("/peliculas/{id}")
	public ResponseEntity<Pelicula> getPeliculaPorId(@PathVariable Long id) {
		Pelicula pelicula = peliculaRepository.findById(id).orElse(null);

		if (pelicula != null) {
			return new ResponseEntity<>(pelicula, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Actualizar pelicula

	@PutMapping("/peliculas/{id}")
	public ResponseEntity<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula peliculaDatos) {
		Pelicula pelicula = peliculaRepository.findById(id).orElse(null);

		if (pelicula != null) {
			pelicula.setTitulo(peliculaDatos.getTitulo());
			pelicula.setGenero(peliculaDatos.getGenero());
			pelicula.setAnioEstreno(peliculaDatos.getAnioEstreno());
			pelicula.setDuracionMinutos(peliculaDatos.getDuracionMinutos());
			pelicula.setDirector(peliculaDatos.getDirector());
			pelicula.setSinopsis(peliculaDatos.getSinopsis());
			pelicula.setDisponible(peliculaDatos.getDisponible());
			pelicula.setImagen(peliculaDatos.getImagen());

			Pelicula peliculaActualizada = peliculaRepository.save(pelicula);
			return new ResponseEntity<>(peliculaActualizada, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Eliminar pelicula por id
	@DeleteMapping("/peliculas/{id}")
	public ResponseEntity<?> deletePelicula(@PathVariable Long id) {
		try {
			peliculaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
