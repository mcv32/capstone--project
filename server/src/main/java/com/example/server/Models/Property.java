package com.example.server.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name= "property")
@Getter
@Setter
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_seq")
    @SequenceGenerator(name = "property_seq", sequenceName = "PROPERTY_SEQUENCE", allocationSize = 1)
    private Long property_id;
    private String name;
    private String address_street;
    private String address_line_2;
    private String city;
    private String state;
    private String zip;
    private String status;
    private double property_balance;

    public Property(Long property_id, String name, String address_street, String address_line_2, String city, String state, String zip, String status, double property_balance) {
        this.property_id = property_id;
        this.name = name;
        this.address_street = address_street;
        this.address_line_2 = address_line_2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.status = status;
        this.property_balance = property_balance;
    }

    public Long getProperty_id() {
        return property_id;
    }

    public void setProperty_id(Long property_id) {
        this.property_id = property_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getProperty_balance() {
        return property_balance;
    }

    public void setProperty_balance(double property_balance) {
        this.property_balance = property_balance;
    }
}
