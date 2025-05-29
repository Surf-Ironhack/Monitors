package com.Surf.monitors_surf.service;

import com.Surf.monitors_surf.dto.ClassesDTO;
import com.Surf.monitors_surf.dto.ResponseDTO;
import com.Surf.monitors_surf.exceptions.MonitorsNotFound;
import com.Surf.monitors_surf.feignMonitors.ClassesFeignMonitors;
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

    @Autowired
    private ClassesFeignMonitors classesFeignMonitors;

    public Monitors postCreateMonitor(Monitors monitor){
        return monitorsRepository.save(monitor);
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

    public Monitors patchChangeSpecialtyLevel(Long id, Monitors monitors){
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

    public ResponseEntity<?> getMonitorsAndClassById(Long id){
        Optional<Monitors> optionalMonitors = monitorsRepository.findById(id);
        System.out.println("ESTO ES UN OPTIONAL: "+optionalMonitors);
        if(optionalMonitors.isPresent()){
            System.out.println("ESTO ES DESPUES DEL GET() "+ optionalMonitors.get());
            // Sacamos el monitor del optional
            Monitors monitorFueraDelOptional = optionalMonitors.get();

            // sacamos el ID de la classe
            Long idDelaClase = monitorFueraDelOptional.getClassesId();

            ClassesDTO classesDTO = classesFeignMonitors.getClassesById(idDelaClase);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(optionalMonitors.get(), classesDTO));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Monitor no encontrado"));

    }
}
