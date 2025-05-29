package com.Surf.monitors_surf.dto;

import com.Surf.monitors_surf.models.Monitors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private Monitors monitors;
    private ClassesDTO classInfo;
}
