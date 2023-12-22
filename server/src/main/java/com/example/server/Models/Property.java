package com.example.server.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "property")
@Getter
@Setter
@NoArgsConstructor
public class Property {
    @SequenceGenerator(
            name = "property_sequence",
            sequenceName = "property_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "property_sequence"
    )
    private int property_id;
    private String name;
    private String address_street;
    private String address_line_2;
    private String city;
    private String state;
    private String zip;
    private double property_balance;

    public Property(int property_id, String name, String address_street, String address_line_2, String city, String state, String zip, double property_balance, boolean balance) {
        this.property_id = property_id;
        this.name = name;
        this.address_street = address_street;
        this.address_line_2 = address_line_2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.property_balance = property_balance;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }
}
