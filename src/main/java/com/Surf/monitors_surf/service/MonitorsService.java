package com.Surf.monitors_surf.service;

import com.Surf.monitors_surf.models.Monitors;
import com.Surf.monitors_surf.repository.MonitorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Service
public class MonitorsService {

    @Autowired
    private MonitorsRepository monitorsRepository;

    public Monitors postCreateMonitor(Monitors monitors){
        return monitorsRepository.save(monitors);
    }

    public ResponseEntity<?> getMonitorsById(Long id){
        Optional<Monitors> monitors = monitorsRepository.findById(id);

        if(monitors.isPresent()){
            return ResponseEntity.ok(monitors.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Monitor no encontrado"));
    }

    public void deleteMonitorsById(Long id){
        monitorsRepository.deleteById(id);
    }

    public Monitors changeSpecialtyLevel(Long id, Monitors monitors){
        Monitors existingMonitor = monitorsRepository.findById(id).orElse(null);

        if(existingMonitor == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Monitor no encontrado");
        }

        if(monitors.getSpecialtyLevel() != null){
            existingMonitor.setSpecialtyLevel(monitors.getSpecialtyLevel());
            monitorsRepository.save(existingMonitor);
        }
        return monitorsRepository.save(existingMonitor);
    }

    public Monitors getFindMonitorById(Long id) {
        return monitorsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Monitor no encontrado"));
    }

}
