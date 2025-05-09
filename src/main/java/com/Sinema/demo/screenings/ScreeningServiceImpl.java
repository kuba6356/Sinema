package com.Sinema.demo.screenings;

import com.Sinema.demo.movies.MovieRepository;
import com.Sinema.demo.tickets.Ticket;
import com.Sinema.demo.tickets.TicketServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final TicketServiceImpl ticketService;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository, MovieRepository movieRepository, TicketServiceImpl ticketService) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.ticketService = ticketService;
    }

    @Override
    public Screening createScreening(CreateScreeningDTO newScreening) {
        try {
            return new Screening(newScreening.getDate(),
                    newScreening.getTime(),
                    newScreening.getAvailableSeats(),
                    movieRepository.findByName(newScreening.getMovieTitle()));

        }catch (Exception e){
            //TODO add custom exception
            return null;
        }
    }

    @Override
    public Screening getScreening(Long id) {
        return screeningRepository.findById(id).get();
    }

    @Override
    public List<Screening> getAllMovieScreenings(String title) {
        try {
            return screeningRepository.findAllByMovie(movieRepository.findByName(title));

        }catch (Exception e){
            //TODO add custom exception
            return null;
        }
    }

    public String deleteScreening(Long id) {
        try {
            screeningRepository.deleteById(id);
            return "Screening deleted";
        }catch (Exception e){
            //TODO add custom exception
            return null;
        }
    }

    @Override
    public Screening changeScreeningDetails(Long id, CreateScreeningDTO changedScreening) {
        try {
            Screening oldScreening = screeningRepository.findById(id).get();
            if(oldScreening == null){
                //TODO add custom exception
            }
            oldScreening.setDate(changedScreening.getDate());
            oldScreening.setTime(changedScreening.getTime());
            oldScreening.setAvailableSeats(changedScreening.getAvailableSeats());
            for(Ticket ticket : oldScreening.getTickets()){
                if(ticket.getSeat() >= changedScreening.getAvailableSeats()){
                    ticketService.cancel(ticket);
                }
            }
            oldScreening.setMovie(movieRepository.findByName(changedScreening.getMovieTitle()));
            screeningRepository.save(oldScreening);
            return oldScreening;
        }catch (Exception e){
            //TODO add custom exception
            return null;
        }
    }
}
