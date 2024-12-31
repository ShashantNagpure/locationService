package com.locationservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.locationservice.dto.LocationDTO;
import com.locationservice.entity.Location;
import com.locationservice.service.LocationService;
//import com.vividsolutions.jts.geom.Coordinate;
//import com.vividsolutions.jts.geom.GeometryFactory;
//import com.vividsolutions.jts.geom.Point;

@RestController
public class LocationController {
	
    @Autowired
    private LocationService locationService;

    @PostMapping("/location")
    public LocationDTO createLocation(@RequestBody LocationDTO locationDTO) {
    	
    	Location location = new Location();
    	
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(locationDTO.getX(),locationDTO.getY()));
        location.setCoordinates(point);
        
        location.setName(locationDTO.getName());

        location = locationService.saveLocation(location);
        
        LocationDTO res = new LocationDTO();
        res.setId(location.getId());
        res.setName(location.getName());
        res.setX(location.getCoordinates().getX());
        res.setY(location.getCoordinates().getY());
        
        return res;
        
    }
    
    @GetMapping("/location/{id}")
    public LocationDTO getLocationById(@PathVariable Integer id) {
        Location location = locationService.getLocationById(id);
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setX(location.getCoordinates().getX());
        locationDTO.setY(location.getCoordinates().getY());
        
        return locationDTO;
    }
    
    @GetMapping("/location/distance")
    public Double getLocationDistance(@RequestBody LocationDTO locationDTO) {
        
        return locationService.getDistance(locationDTO.getId(), locationDTO.getX(), locationDTO.getY());
    }
    
    @GetMapping("/location/nearest")
    public List<LocationDTO> getNearest(@RequestBody LocationDTO locationDTO) {
        
        return locationService.getNearestLocation(locationDTO.getX(), locationDTO.getY()).stream().map(location->{
        	LocationDTO dto = new LocationDTO();
        	dto.setId(location.getId());
        	dto.setName(location.getName());
        	dto.setX(location.getCoordinates().getX());
        	dto.setY(location.getCoordinates().getY());
        	return dto;
        }).collect(Collectors.toList());
    }

}
