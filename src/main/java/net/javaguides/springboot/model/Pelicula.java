package net.javaguides.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Peliculas")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private String genero;

	private Integer anioEstreno;

	private Integer duracionMinutos;

	private String director;

	@Column(length = 1000)
	private String sinopsis;

	private String imagen;

	private Boolean disponible;
	
	public Pelicula() {
		
	}

	public Pelicula(String titulo, String genero, Integer anioEstreno, Integer duracionMinutos, String director,
			String sinopsis, String imagen, Boolean disponible) {
		super();
		this.titulo = titulo;
		this.genero = genero;
		this.anioEstreno = anioEstreno;
		this.duracionMinutos = duracionMinutos;
		this.director = director;
		this.sinopsis = sinopsis;
		this.imagen = imagen;
		this.disponible = disponible;
	}

	// Getters y setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getAnioEstreno() {
		return anioEstreno;
	}

	public void setAnioEstreno(Integer anioEstreno) {
		this.anioEstreno = anioEstreno;
	}

	public Integer getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(Integer duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

}
