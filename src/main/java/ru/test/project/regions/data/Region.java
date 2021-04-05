package ru.test.project.regions.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.project.regions.dto.RegionTransferObject;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class Region {
    @NotNull
    @Positive
    private long id;

    @NotEmpty
    @NotBlank
    @Max(100)
    private String name;

    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 3)
    private String reduction;

    public Region(RegionTransferObject regionTransferObject) {
        this.name = regionTransferObject.getName();
        this.reduction = regionTransferObject.getReduction();
    }
}


