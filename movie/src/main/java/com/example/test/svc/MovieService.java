package com.example.test.svc;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dao.MovieDao;
import com.example.test.entity.MovieEntity;
import com.example.test.exception.RecordNotFoundException;

@Service
public class MovieService {
	private static final Logger logger = Logger.getLogger(MovieService.class);
	
	private final MovieDao movieDao;
	
	@Autowired
	public MovieService(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	public List<MovieEntity> getAllMovie() {
		List<MovieEntity> listMovie = movieDao.findAll();
		
		if (listMovie.size() > 0) {
			return listMovie;
		}
		
		return null;
	}
	
	public MovieEntity getMovieById(int id) throws RecordNotFoundException {
		Optional<MovieEntity> movie = movieDao.findById(id);
		
		if (movie.isPresent()) {
			return movie.get();
		}
		
		return null;
	}
	
	public MovieEntity createMovie(MovieEntity movie) {
		movie = movieDao.save(movie);
		
		return movie;
	}
	
	public MovieEntity updateMovie(int id, MovieEntity movie) {
		Optional<MovieEntity> data = movieDao.findById(id);
		
		if (data.isPresent()) {
			MovieEntity newData = data.get();
			newData.setTitle(movie.getTitle());
			newData.setDescription(movie.getDescription());
			newData.setRating(movie.getRating());
			newData.setImage(movie.getImage());
			newData.setUpdatedAt(new Date());
			
			newData = movieDao.save(newData);
			
			return newData;			
		}
		
		return null;
	}
	
	public int deleteMovie(int id) {
		Optional<MovieEntity> movie = movieDao.findById(id);
		
		if (movie.isPresent()) {
			movieDao.deleteById(id);
			
			return 1;
		} else {
			logger.info("No movie record exist for given id " + id);
//			throw new RecordNotFoundException("No movie record exist for given id", id);
		}
		
		return 0;
	}
}
