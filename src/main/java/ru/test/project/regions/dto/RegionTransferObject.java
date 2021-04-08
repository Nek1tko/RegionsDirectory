package ru.test.project.regions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegionTransferObject {
    @NotBlank(message = "Name must be not blank")
    @Size(max = 100, message = "Name must be not grater then 100 symbols")
    String name;

    @NotBlank(message = "Reduction must be not blank")
    @Size(min = 3, max = 3, message = "Reduction must be size 3")
    String reduction;
}
