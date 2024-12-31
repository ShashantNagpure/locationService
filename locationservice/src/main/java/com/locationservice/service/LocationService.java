package com.locationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locationservice.entity.Location;
import com.locationservice.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location getLocationById(Integer id) {
        return locationRepository.findById(id).orElse(null);
    }
    
    public Double getDistance(Integer id, Double longitude, Double latitude) {
    	
    	return locationRepository.getDistance(id,longitude,latitude);
    }
    
    public List<Location>getNearestLocation(Double longitude,Double latitude){
    	
    	return locationRepository.getNearestLocations(longitude,latitude);
    	
    }
}
