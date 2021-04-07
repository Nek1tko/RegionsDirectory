package ru.test.project.regions.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.test.project.regions.data.Region;
import ru.test.project.regions.dto.RegionTransferObject;
import ru.test.project.regions.exeption.InvalidIdException;
import ru.test.project.regions.mapper.RegionMapper;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    private RegionMapper regionMapper;

    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Override
    public List<Region> getRegions() {
        return regionMapper.findAll();
    }

    @Override
    @Cacheable(value = "regions")
    public Region getRegion(Long id) {
        if (id == null) {
            throw new InvalidIdException("Id must be not null");
        }
        if (id <= 0) {
            throw new InvalidIdException("Id must be positive");
        }

        return regionMapper.findById(id).orElseThrow(() ->
                new InvalidIdException("Data base has no element with such id")
        );
    }

    @Override
    @CachePut(value = "regions", key = "#result.id")
    public Region addRegion(RegionTransferObject regionTransferObject) {
        Region region = new Region(regionTransferObject);
        regionMapper.save(region);
        return region;
    }

    @Override
    @CacheEvict(value = "regions")
    public void deleteRegionById(Long id) {
        if (id == null) {
            throw new InvalidIdException("Id must be not null");
        }
        if (id <= 0) {
            throw new InvalidIdException("Id must be positive");
        }

        if (regionMapper.delete(id) <= 0) {
            throw new InvalidIdException("Data base has no element with such id");
        }
    }

    @Override
    @CachePut(value = "regions", key = "#result.id")
    public Region updateRegion(Region region) {
        if (regionMapper.update(region) <= 0) {
            throw new InvalidIdException("Data base has no element with such id");
        }

        return region;
    }
}
