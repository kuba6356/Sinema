package com.Sinema.demo.movies;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(name = "movie_seq", sequenceName = "movie_sequence", allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private List<String> themes = new ArrayList<>();
    private String coverUrl;
    private Double cost;

    public Movie(String name, String description, List<String> themes, String coverUrl, Double cost) {
        this.name = name;
        this.description = description;
        this.themes = themes;
        this.coverUrl = coverUrl;
        this.cost = cost;
    }
    public Movie(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(description, movie.description) && Objects.equals(themes, movie.themes) && Objects.equals(coverUrl, movie.coverUrl) && Objects.equals(cost, movie.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, themes, coverUrl, cost);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", themes=" + themes +
                ", coverUrl='" + coverUrl + '\'' +
                ", cost=" + cost +
                '}';
    }
}
