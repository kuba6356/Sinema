package com.Sinema.demo.tickets;

import jakarta.persistence.*;

@Entity
@Table(name ="tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true)
    private String ticket_code;
    private boolean used;



}
