package ru.test.project.regions.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.project.regions.dto.RegionTransferObject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class Region {
    @Positive(message = "Id must be positive")
    private Long id;

    @NotBlank(message = "Name must be not blank")
    @Size(max = 100, message = "Name must be not grater then 100 symbols")
    private String name;

    @NotBlank(message = "Reduction must be not blank")
    @Size(min = 3, max = 3, message = "Reduction must be size 3")
    private String reduction;

    public Region(RegionTransferObject regionTransferObject) {
        this.name = regionTransferObject.getName();
        this.reduction = regionTransferObject.getReduction();
    }
}


