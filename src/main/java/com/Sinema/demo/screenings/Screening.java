package com.Sinema.demo.screenings;

import com.Sinema.demo.tickets.Ticket;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name ="screenings")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private int[] availableSeats;
    private boolean canceled;
    private boolean scanned;
    @OneToMany(
            cascade = CascadeType.REFRESH
    )
    @JoinColumn(
            name = "screening_Id",
            referencedColumnName = "id"
    )
    private List<Ticket> tickets;

    public Screening(LocalDate date, LocalTime time, int availableSeats) {
        this.date = date;
        this.time = time;
        this.availableSeats = new int[availableSeats];
        for(int temp = 0; temp < availableSeats; temp++){
            this.availableSeats[temp] = temp;
        }
        this.canceled = false;
        this.scanned = false;
        this.tickets = new ArrayList<>();
    }

    public Screening() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int[] getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int[] availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isScanned() {
        return scanned;
    }

    public void setScanned(boolean scanned) {
        this.scanned = scanned;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return canceled == screening.canceled && scanned == screening.scanned && Objects.equals(id, screening.id) && Objects.equals(date, screening.date) && Objects.equals(time, screening.time) && Objects.deepEquals(availableSeats, screening.availableSeats) && Objects.equals(tickets, screening.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, Arrays.hashCode(availableSeats), canceled, scanned, tickets);
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", availableSeats=" + Arrays.toString(availableSeats) +
                ", canceled=" + canceled +
                ", scanned=" + scanned +
                ", tickets=" + tickets +
                '}';
    }
}
