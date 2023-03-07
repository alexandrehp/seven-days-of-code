package br.com.alura.service;

import br.com.alura.controller.FilmController;
import br.com.alura.model.Movie;
import br.com.alura.util.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FilmServiceImpl implements FilmService {

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Override
    public List<Movie> getFilm(){

        List<Movie> retorno = new ArrayList<Movie>();

        try {
            String imdbKey = "teste";
            String uri = "https://imdb-api.com/en/API/Top250Movies/" + imdbKey;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(null, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

            var jsonString = response.getBody();

            var parser = new JsonParser();

            List<Map<String, String>> listaDeFilmes = parser.parse(jsonString);

            for (Map<String, String> filme : listaDeFilmes) {

                retorno.add(new Movie(filme.get("id"),
                        filme.get("rank"),
                        filme.get("title"),
                        filme.get("fullTitle"),
                        filme.get("year"),
                        filme.get("image"),
                        filme.get("crew"),
                        filme.get("imDbRating"),
                        filme.get("imDbRatingCount")));
            }

        } catch (HttpClientErrorException e) {
            logger.error("Erro ao chamar o endpoint movie: ", e);
        } catch (Exception e) {
            logger.error("Erro geral ao chamar o endpoint movie: ", e);
        }

        return retorno;

    }
}
