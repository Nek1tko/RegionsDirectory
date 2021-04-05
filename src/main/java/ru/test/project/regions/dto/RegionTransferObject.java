package ru.test.project.regions.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegionTransferObject {
    @NotEmpty
    @NotBlank
    @Max(100)
    String name;

    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 3)
    String reduction;
}
