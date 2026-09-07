package org.manchirevula.locationtrackerserver.controller;

import org.manchirevula.locationtrackerserver.model.LocationRecord;
import org.manchirevula.locationtrackerserver.repository.LocationRecordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationRecordRepository repository;

    public LocationController(LocationRecordRepository repository) {
        this.repository = repository;
    }

    // API 1: POST - receive JSON and store
    @PostMapping
    public ResponseEntity<?> receiveLocation(@RequestBody LocationRecord record) {
        System.out.println("Received location record: " + record.toString());
        repository.save(record);
        Map<String, String> resp = new HashMap<>();
        resp.put("status", "success");
        return ResponseEntity.ok(resp);
    }

    // API 2: GET latest record by timestamp
    @GetMapping("/latest")
    public ResponseEntity<?> getLatest() {
        return repository.findTopByOrderByTimestampDesc()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}