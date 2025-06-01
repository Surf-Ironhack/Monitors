package com.Surf.monitors_surf.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monitors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Debes proporcionar tu nombre")
    private String nameStaff;

    @Pattern(regexp = "^(Principiante|Intermedio|Avanzado|Competición)$",
            message = "El nivel debe ser: Principiante, Intermedio, Avanzado y Competición")
    private String specialtyLevel;
    private Long classesId;
}
