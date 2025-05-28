package com.Surf.monitors_surf.feignMonitors;

import com.Surf.monitors_surf.dto.ClassesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "class-surf")
public interface ClassesFeignMonitors {

    @GetMapping("/classes/{id}")
    ClassesDTO getClassesById(@PathVariable Long id);

}
