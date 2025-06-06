package com.Surf.monitors_surf.controllers;

import com.Surf.monitors_surf.dto.ClassesDTO;
import com.Surf.monitors_surf.exceptions.MonitorsNotFound;
import com.Surf.monitors_surf.feignMonitors.ClassesFeignMonitors;
import com.Surf.monitors_surf.models.Monitors;
import com.Surf.monitors_surf.repository.MonitorsRepository;
import com.Surf.monitors_surf.service.MonitorsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitors")
public class MonitorsController {

    @Autowired
    private MonitorsRepository monitorsRepository;

    @Autowired
    private ClassesFeignMonitors classesFeignMonitors;

    @Autowired
    MonitorsService monitorsService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<?> getFindMonitorById(@PathVariable Long id) {
        return monitorsService.getMonitorsById(id);
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<?> getMonitorsAndClassById(@PathVariable Long id){
            return monitorsService.getMonitorsAndClassById(id);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.PROCESSING)
    private ResponseEntity<Void> deleteMonitors(@PathVariable Long id){
        monitorsService.deleteMonitorsById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Monitors createMonitor(@RequestBody @Valid Monitors monitor) {
        return monitorsService.postCreateMonitor(monitor);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Monitors>puthChangeSpecialtyLevel(@PathVariable Long id,@RequestBody @Valid Monitors monitors){
        Monitors newLevelMonitor = monitorsService.patchChangeSpecialtyLevel(id, monitors);
        return ResponseEntity.ok(newLevelMonitor);
    }
}
