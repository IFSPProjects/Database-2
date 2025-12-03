package com.database2.storage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database2.storage.dto.request.LocationPostRequest;
import com.database2.storage.dto.response.LocationListResponse;
import com.database2.storage.dto.response.LocationResponse;
import com.database2.storage.entity.Location;
import com.database2.storage.repository.LocationRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/storage")
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @PostMapping("/addLocation")
    public ResponseEntity<LocationResponse> addLocation(@Valid @RequestBody LocationPostRequest request) {

        Location location = new Location();
        location.setName(request.name());
        location.setDescription(request.description());

        locationRepository.save(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(new LocationResponse("Success", location));
    }

    @PutMapping("/updateLocation/{id}")
    public ResponseEntity<LocationResponse> updateLocation(@Valid @RequestBody LocationPostRequest request, @PathVariable("id") int id) {

        Location location = new Location();
        location.setId(id);
        location.setName(request.name());
        location.setDescription(request.description());

        locationRepository.save(location);
        return ResponseEntity.status(HttpStatus.OK).body(new LocationResponse("Success", location));
    }

    @DeleteMapping("/deleteLocation/{id}")
    public ResponseEntity<LocationResponse> deleteClient(@PathVariable("id") int id) {
        Optional<Location> location = locationRepository.findById(id);
        if (!location.isEmpty() && location.isPresent()) {
            locationRepository.delete(location.get());
            return ResponseEntity.status(HttpStatus.OK).body(new LocationResponse("Success", location.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LocationResponse("Nothing was deleted. Verify if the correct ID is inserted.", null));
        }
    }

    @GetMapping("/getLocation/{id}")
    public ResponseEntity<LocationResponse> getLocation(@PathVariable("id") int id) {
        Location location = locationRepository.findLocationByid(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new LocationResponse("Success", location));
    }

    @GetMapping("/getLocations")
    public ResponseEntity<LocationListResponse> getLocations() {
        List<Location> locations = locationRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new LocationListResponse("Success", locations));
    }
    
}
