package com.example.server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "message")
public class Message {
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private int message_id;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "email"
    )
    private AppUser appUser;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "property_id"
    )
    private Property property;

    public Message(int message_id, AppUser appUser, Property property) {
        this.message_id = message_id;
        this.appUser = appUser;
        this.property = property;
    }
}
