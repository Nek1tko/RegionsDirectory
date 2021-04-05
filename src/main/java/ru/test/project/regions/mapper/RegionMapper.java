package ru.test.project.regions.mapper;

import org.apache.ibatis.annotations.*;
import ru.test.project.regions.data.Region;

import java.util.List;

@Mapper
public interface RegionMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "reduction", column = "reduction")
    })
    @Select("SELECT * FROM Regions")
    List<Region> findAll();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO Regions (name, reduction) VALUES (#{name}, #{reduction})")
    void save(Region region);

    @Update("UPDATE Regions SET name = #{name}, reduction = #{reduction} WHERE id = #{id}")
    void update(Region region);
}
