package ru.test.project.regions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.project.regions.data.Region;
import ru.test.project.regions.dto.RegionTransferObject;
import ru.test.project.regions.mapper.RegionMapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {
    private final RegionMapper regionMapper;

    public RegionController(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @GetMapping
    public ResponseEntity<List<Region>> getRegions() {
        return new ResponseEntity<>(regionMapper.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Region> addRegion(@Valid @RequestBody RegionTransferObject regionTransferObject) {
        Region region = new Region(regionTransferObject);
        regionMapper.save(region);
        System.out.println(region);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Region> updateRegion(@Valid @RequestBody Region region) {
        regionMapper.update(region);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }
}
