package ru.test.project.regions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.test.project.regions.data.Region;
import ru.test.project.regions.dto.RegionTransferObject;
import ru.test.project.regions.exeption.InvalidIdException;
import ru.test.project.regions.mapper.RegionMapper;
import ru.test.project.regions.service.RegionServiceImpl;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class RegionsApplicationServiceTests {
    @Autowired
    private RegionServiceImpl regionService;

    @Autowired
    private RegionMapper regionMapper;

    @AfterEach
    private void clean() {
        regionMapper.deleteAll();
    }

    @Test
    public void testDeleteOperationNegativeId() {
        Exception exception = assertThrows(InvalidIdException.class, () -> regionService.deleteRegionById(-1L));
        assertEquals(exception.getMessage(), "Id must be positive");
    }

    @Test
    public void testDeleteOperationNullId() {
        Exception exception = assertThrows(InvalidIdException.class, () -> regionService.deleteRegionById(null));
        assertEquals(exception.getMessage(), "Id must be not null");
    }

    @Test
    public void testDeleteOperationNoSuchEl() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> regionService.deleteRegionById(Long.MAX_VALUE));
        assertEquals(exception.getMessage(), "Data base has no element with such id");
    }

    @Test
    public void testDeleteOperation() {
        Region region = regionService.addRegion(new RegionTransferObject("Ленинградская область", "ЛЕН"));
        Long id = region.getId();
        regionService.deleteRegionById(id);
        Exception exception = assertThrows(NoSuchElementException.class, () -> regionService.getRegion(id));
        assertEquals(exception.getMessage(), "Data base has no element with such id");
    }

    @Test
    public void testAddOperation() {
        RegionTransferObject region = new RegionTransferObject("Москва", "МОС");
        Region addedRegion = regionService.addRegion(region);
        assertEquals(regionService.getRegion(addedRegion.getId()), addedRegion);
    }

    @Test
    public void testUpdateOperation() {
        Region region = regionService.addRegion(new RegionTransferObject("Ленинградская область", "ЛЕН"));
        region.setName("Московская область");
        region.setReduction("МСК");
        assertEquals(regionService.updateRegion(region), region);
    }

    @Test
    public void testUpdateOperationNoSuchId() {
        Region region = regionService.addRegion(new RegionTransferObject("Ленинградская область", "ЛЕН"));
        region.setId(Long.MAX_VALUE);
        Exception exception = assertThrows(NoSuchElementException.class, () -> regionService.updateRegion(region));
        assertEquals(exception.getMessage(), "Data base has no element with such id");
    }

    @Test
    public void testDuplicateName() {
        regionService.addRegion(new RegionTransferObject("Ленинградская область", "ЛЕН"));
        assertThrows(DuplicateKeyException.class,
                () -> regionService.addRegion(new RegionTransferObject("Ленинградская область", "ЛЕН"))
        );
    }

    @Test
    public void testDuplicateReduction() {
        regionService.addRegion(new RegionTransferObject("Ленинградская область", "ЛЕН"));
        assertThrows(DuplicateKeyException.class,
                () -> regionService.addRegion(new RegionTransferObject("Московская область", "ЛЕН"))
        );
    }

}
