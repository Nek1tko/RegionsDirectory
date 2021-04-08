package ru.test.project.regions.service;

import ru.test.project.regions.data.Region;
import ru.test.project.regions.dto.RegionTransferObject;

import java.util.List;

public interface RegionService {
    List<Region> getRegions();

    Region getRegion(Long id);

    Region addRegion(RegionTransferObject regionTransferObject);

    void deleteRegionById(Long id);

    Region updateRegion(Region region);

}
