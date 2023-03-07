package br.com.alura.controller;

import br.com.alura.model.Movie;
import br.com.alura.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
public class TestController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    private List<Movie> getFilms() {
        return filmService.getFilm();
    }

}
