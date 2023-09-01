package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>("new movie added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new ResponseEntity<>("new director added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie_name, @RequestParam("director") String director) {
        //Pass movie name and director name as request parameters
        //Return success message wrapped in a ResponseEntity object

        boolean added = movieService.addMovieDirectorPair(movie_name,director);
        if(added) return new ResponseEntity<>("new movie-director-pair added successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("failed",HttpStatus.EXPECTATION_FAILED);

    }

    //Get Movie by movie name: GET /movies/get-movie-by-name/{name}
    //Pass movie name as path parameter
    //Return Movie object wrapped in a ResponseEntity object
    //Controller Name - getMovieByName
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movie_name) {
        Movie movie = movieService.getMovieByName(movie_name);
        if(movie==null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(movie,HttpStatus.FOUND);

    }

    //Get Director by director name: GET /movies/get-director-by-name/{name}
    //Pass director name as path parameter
    //Return Director object wrapped in a ResponseEntity object
    //Controller Name - getDirectorByName
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String director_name) {
        Director director = movieService.getDirectorByName(director_name);
        if(director==null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }

    //Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    //Pass director name as path parameter
    //Return List of movies name(List()) wrapped in a ResponseEntity object
    //Controller Name - getMoviesByDirectorName
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName) {
        List<String> list = movieService.getMoviesByDirectorName(directorName);
        if(list==null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }

    //Get List of all movies added: GET /movies/get-all-movies
    //No params or body required
    //Return List of movies name(List()) wrapped in a ResponseEntity object
    //Controller Name - findAllMovies

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> list = movieService.findAllMovies();
        return new ResponseEntity<>(list,HttpStatus.CREATED);
    }

    //Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    //Pass director’s name as request parameter
    //Return success message wrapped in a ResponseEntity object
    //Controller Name - deleteDirectorByName
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String directorName) {
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("Director deleted",HttpStatus.CREATED);
    }

    //Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
    //No params or body required
    //Return success message wrapped in a ResponseEntity object
    //Controller Name - deleteAllDirectors
    //(Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("all directors deleted",HttpStatus.CREATED);
    }
}
