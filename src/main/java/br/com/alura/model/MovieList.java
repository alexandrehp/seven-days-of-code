package br.com.alura.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieList {

    public MovieList() {
        super();
    }

    public MovieList(List<Movie> items) {
        this.items = items;
    }

    @JsonProperty("items")
    public List<Movie> items;

    public List<Movie> getItems() {
        return items;
    }

    public void setItems(List<Movie> items) {
        this.items = items;
    }
}
