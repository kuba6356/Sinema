package com.Sinema.demo.screenings;

import java.util.List;

public interface ScreeningService {
    Screening createScreening(CreateScreeningDTO newScreening);

    Screening getScreening(Long id);

    List<Screening> getAllMovieScreenings(String title);

    Screening changeScreeningDetails(Long id, CreateScreeningDTO changedScreening);

    String deleteScreening(Long id);
}
