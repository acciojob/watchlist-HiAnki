package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public boolean addMovie(Movie movie) {
        movieRepository.addMovie(movie);
        return true;
    }

    public void addDirector(Director director) {
        movieRepository.addDirector(director);
    }

    public boolean addMovieDirectorPair(String movieName, String director) {
        // check if they exist in data base
        if(movieRepository.findMovie(movieName) && movieRepository.findDirector(director)) {
            movieRepository.addMovieDirectorPair(movieName,director);
            return true;
        }else return false;
    }


    public Movie getMovieByName(String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        Set<String> movie_list = movieRepository.getMovie_repo().keySet();
        List<String> list = new ArrayList<>();
        for(String m: movie_list) {
            list.add(m);
        }
        return list;
    }

    public void deleteDirectorByName(String directorName) {
        movieRepository.deleteDirectorByName(directorName);
    }

    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }
}
