package com.locationservice.entity;

import java.io.Serializable;

import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

//import com.vividsolutions.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "coordinates")
    private Point coordinates;

    public Location() {}
    
    public Location(String name, Point coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }
}
