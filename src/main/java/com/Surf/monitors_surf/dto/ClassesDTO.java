package com.Surf.monitors_surf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassesDTO {

    private Long id;
    private String nameClass;
    private String level;
    private LocalDate date;
}
