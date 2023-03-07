package br.com.alura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class FilmeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/top250")
    public ListOfMovies getTop250Filmes() {

        ResponseEntity<ListOfMovies> response =
                this.restTemplate.getForEntity("https://imdb-api.com/en/API/Top250Movies/k_kevl4t4w", ListOfMovies.class);

        return response.getBody();
    }

    record Movie(String title, String image, String year, String imDbRating) {
    }

    record ListOfMovies(List<Movie> items) {
    }

}
