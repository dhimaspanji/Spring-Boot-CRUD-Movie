package com.example.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.entity.MovieEntity;
import com.example.test.model.Response;
import com.example.test.svc.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	private final MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping
	public @ResponseBody Object getAllMovie() {
		List<MovieEntity> listMovie = movieService.getAllMovie();
		
		if (listMovie != null) {
			return listMovie;
		} else {
			Response resp = new Response();
			resp.setResultCode(404);
			resp.setMessage("Data not found");
			
			return resp;
		}
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Object getMovieById(@PathVariable("id") int id) {
		MovieEntity listMovie = movieService.getMovieById(id);
		
		if (listMovie != null) {
			return listMovie;
		} else {
			Response resp = new Response();
			resp.setResultCode(404);
			resp.setMessage("Data not found");
			
			return resp;
		}
	}
	
	@PostMapping
	public ResponseEntity<MovieEntity> createMovie(@Validated @RequestBody MovieEntity movie) {
		MovieEntity save = movieService.createMovie(movie);
		return new ResponseEntity<MovieEntity>(save, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public @ResponseBody Object updateMovie(@PathVariable("id") int id, @Validated @RequestBody MovieEntity movie) {
		MovieEntity save = movieService.updateMovie(id, movie);
		
		if (save != null) {			
			return new ResponseEntity<MovieEntity>(save, new HttpHeaders(), HttpStatus.OK);
		} else {
			Response resp = new Response();
			resp.setResultCode(404);
			resp.setMessage("Data not found");
			
			return resp;
		}
	}
	
	@DeleteMapping("/{id}")
	public Response deleteMovie(@PathVariable("id") int id) {
		Response resp = new Response();

		int delete = movieService.deleteMovie(id);
		
		if (delete > 0) {
			resp.setResultCode(200);
			resp.setMessage("Delete successfully");
		} else {
			resp.setResultCode(404);
			resp.setMessage("Data not found. Delete failed");
		}
		
		return resp;
	}
}
