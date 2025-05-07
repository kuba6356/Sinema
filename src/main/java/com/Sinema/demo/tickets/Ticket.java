package com.Sinema.demo.tickets;

import com.Sinema.demo.screenings.Screening;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name ="tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tickets_seq")
    @SequenceGenerator(name = "tickets_seq", sequenceName = "tickets_sequence", allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true)
    private String ticket_code;
    private boolean used;
    private boolean canceled;
    @ManyToOne(
            cascade = CascadeType.REFRESH
    )
    @JoinColumn(
            name = "screening_id",
            referencedColumnName = "id"
    )
    private Screening screening;

    public Ticket(String ticket_code) {
        this.ticket_code = ticket_code;
        this.used = false;
        this.canceled = false;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicket_code() {
        return ticket_code;
    }

    public void setTicket_code(String ticket_code) {
        this.ticket_code = ticket_code;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return used == ticket.used && canceled == ticket.canceled && Objects.equals(id, ticket.id) && Objects.equals(ticket_code, ticket.ticket_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket_code, used, canceled);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "canceled=" + canceled +
                ", used=" + used +
                ", ticket_code='" + ticket_code + '\'' +
                ", id=" + id +
                '}';
    }
}
