package com.Sinema.demo.screenings;

import com.Sinema.demo.movies.Movie;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateScreeningDTO {
    private LocalDate date;
    private LocalTime time;
    private int availableSeats;
    private String movieTitle;

    public CreateScreeningDTO(LocalDate date, LocalTime time, int availableSeats, String movieTitle) {
        this.date = date;
        this.time = time;
        this.availableSeats = availableSeats;
        this.movieTitle = movieTitle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
