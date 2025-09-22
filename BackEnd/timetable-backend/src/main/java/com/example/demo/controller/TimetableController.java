package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TimetableEntry;
import com.example.demo.service.TimetableService;

@RestController
@RequestMapping("/api/timetable")
@CrossOrigin(origins = "*")  // allow frontend React access
public class TimetableController {

    private final TimetableService service;

    public TimetableController(TimetableService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String home() 
    {
        return "Jenkins Full Stack Deployment Successfull";
    }

    // GET all
    @GetMapping("/all")
    public List<TimetableEntry> getAllEntries() {
        return service.getAllEntries();
    }

    // GET by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<TimetableEntry> getEntryById(@PathVariable Long id) {
        return service.getEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST add
    @PostMapping("/add")
    public TimetableEntry addEntry(@RequestBody TimetableEntry entry) {
        return service.addEntry(entry);
    }

    // PUT update
    @PutMapping("/update")
    public ResponseEntity<TimetableEntry> updateEntry(@RequestBody TimetableEntry entry) {
        if (entry.getId() == null || service.getEntryById(entry.getId()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.updateEntry(entry));
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        service.deleteEntry(id);
        return ResponseEntity.ok().build();
    }
}
