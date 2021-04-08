package ru.test.project.regions.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.project.regions.data.Region;
import ru.test.project.regions.dto.RegionTransferObject;
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

    @ApiResponse(code = 200, message = "Get all regions from database")
    @GetMapping
    public ResponseEntity<List<Region>> getRegions() {
        return new ResponseEntity<>(regionService.getRegions(), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added"),
            @ApiResponse(code = 400, message = "Invalid region parameters"),
            @ApiResponse(code = 409, message = "Duplicate name or reduction")
    })
    @PostMapping
    public ResponseEntity<Region> addRegion(@Valid @RequestBody RegionTransferObject regionTransferObject) {
        return new ResponseEntity<>(regionService.addRegion(regionTransferObject), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Invalid region parameters"),
            @ApiResponse(code = 404, message = "No regions with passed id"),
            @ApiResponse(code = 409, message = "Duplicate name or reduction")
    })
    @PutMapping
    public ResponseEntity<Region> updateRegion(@Valid @RequestBody Region region) {
        regionService.updateRegion(region);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Got region with passed id"),
            @ApiResponse(code = 400, message = "Invalid id"),
            @ApiResponse(code = 404, message = "No regions with passed id")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
        Region region = regionService.getRegion(id);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Delete region with passed id"),
            @ApiResponse(code = 400, message = "Invalid id"),
            @ApiResponse(code = 404, message = "No regions with passed id")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegionById(@PathVariable Long id) {
        regionService.deleteRegionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 }
