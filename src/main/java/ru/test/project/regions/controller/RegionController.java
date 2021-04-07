package ru.test.project.regions.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.project.regions.data.Region;
import ru.test.project.regions.dto.RegionTransferObject;
import ru.test.project.regions.exeption.InvalidIdException;
import ru.test.project.regions.service.RegionService;
import ru.test.project.regions.service.RegionServiceImpl;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionServiceImpl regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<Region>> getRegions() {
        return new ResponseEntity<>(regionService.getRegions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Region> addRegion(@Valid @RequestBody RegionTransferObject regionTransferObject) {
        return new ResponseEntity<>(regionService.addRegion(regionTransferObject), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Region> updateRegion(@Valid @RequestBody Region region) {
        regionService.updateRegion(region);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
        Region region = regionService.getRegion(id);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Region> deleteRegionById(@PathVariable Long id) {
        regionService.deleteRegionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 }
