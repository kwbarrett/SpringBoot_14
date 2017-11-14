package com.example.demo.controller;

import com.example.demo.model.Director;
import com.example.demo.model.Movie;
import com.example.demo.repositories.DirectorRepository;
import com.example.demo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String index(Model model){
        // First let's create a director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        Director director2 = new Director();
        director2.setName("George Roberts");
        director2.setGenre("Drama");

        // Now let's create a movie
        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("About Stars...");



        // Add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("DeathStar Ewoks");
        movie2.setYear(2011);
        movie2.setDescription("About Ewoks on the DeathStar...");

        movies.add(movie2);

        //Another movie
        Movie movie3 = new Movie();
        movie3.setTitle("Runaway Jones");
        movie3.setYear(1985);
        movie3.setDescription("Julia Jones runs away at the altar...");
        Set<Movie> movies2 = new HashSet<>();
        movies2.add(movie3);


        // Add the list of movies to the director's movie list
        director.setMovies(movies);
        director2.setMovies(movies2);

        // Save the director to the database
        directorRepository.save(director);
        directorRepository.save(director2);

        movie2.setDirector(director);
        movie.setDirector(director);
        movie3.setDirector(director2);
        movieRepository.save(movie);
        movieRepository.save(movie2);
        movieRepository.save(movie3);





        // Grad all the directors from the database and send them to
        // the template
        model.addAttribute("directors", directorRepository.findAll());


        return "index";
    }

    @RequestMapping("/directors")
    public String listDirectors(Model model){
        model.addAttribute("directors", directorRepository.findAll());
        return "listDirectors";
    }
}
