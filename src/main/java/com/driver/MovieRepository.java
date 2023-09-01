package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Repository
public class MovieRepository {

    private HashMap<String,Movie> movie_repo;
    private HashMap<String,Director> director_repo;
    private HashMap<String, List<String>> movie_director_pair;

    public MovieRepository(HashMap<String, Movie> movie_repo, HashMap<String, Director> director_repo, HashMap<String, List<String>> movie_director_pair) {
        this.movie_repo = movie_repo;
        this.director_repo = director_repo;
        this.movie_director_pair = movie_director_pair;
    }

    public HashMap<String, Movie> getMovie_repo() {
        return movie_repo;
    }

    public void setMovie_repo(HashMap<String, Movie> movie_repo) {
        this.movie_repo = movie_repo;
    }

    public HashMap<String, Director> getDirector_repo() {
        return director_repo;
    }

    public void setDirector_repo(HashMap<String, Director> director_repo) {
        this.director_repo = director_repo;
    }

    public HashMap<String, List<String>> getMovie_director_pair() {
        return movie_director_pair;
    }

    public void setMovie_director_pair(HashMap<String, List<String>> movie_director_pair) {
        this.movie_director_pair = movie_director_pair;
    }

    public void addMovie(Movie movie) {
        movie_repo.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
        director_repo.put(director.getName(),director);
    }


    public boolean findMovie(String movieName) {
        return movie_repo.containsKey(movieName);
    }

    public boolean findDirector(String director) {
        return director_repo.containsKey(director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        List<String> movies = movie_director_pair.getOrDefault(director,new ArrayList<>());
        movies.add(movie);
        movie_director_pair.put(director,movies);
    }

    public Movie getMovieByName(String movieName) {
        return movie_repo.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return director_repo.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        if(movie_director_pair.containsKey(directorName)) {
            return movie_director_pair.get(directorName);
        }
        return null;
    }

    public void deleteDirectorByName(String directorName) {
        if(director_repo.containsKey(directorName)) director_repo.remove(directorName);
        if(movie_director_pair.containsKey(directorName)==false) return;
        List<String> movies = movie_director_pair.get(directorName);
        for(String m: movies) {
            if(movie_repo.containsKey(m)) movie_repo.remove(m);
        }
        movie_director_pair.remove(directorName);
    }

    public void deleteAllDirectors() {
        List<String> directors = new ArrayList<>();
        for(String s: director_repo.keySet()) directors.add(s);

        director_repo.clear();

        for(String s: directors) deleteDirectorByName(s);
    }
}
