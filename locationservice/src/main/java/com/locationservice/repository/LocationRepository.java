package com.locationservice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locationservice.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location,Integer> {
	

	    @Query(value = "SELECT ST_Distance_Sphere(l.coordinates, POINT(:longitude, :latitude)) " +
	                   "FROM location l WHERE l.id = :id", nativeQuery = true)
	    public Double getDistance(@Param("id") Integer id, @Param("longitude") Double longitude, @Param("latitude") Double latitude);
	    
	
	    
	    @Query(value="SELECT * from location objLoc order by"
	    		+ " ST_Distance_Sphere(objLoc.coordinates, POINT(:longitude, :latitude)) asc",nativeQuery = true)
	    public List<Location>getNearestLocations(@Param("longitude") Double longitude, @Param("latitude") Double latitude);

}
