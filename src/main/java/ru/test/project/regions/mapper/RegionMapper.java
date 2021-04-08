package ru.test.project.regions.mapper;

import org.apache.ibatis.annotations.*;
import ru.test.project.regions.data.Region;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RegionMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "reduction", column = "reduction")
    })
    @Select("SELECT * FROM Regions")
    List<Region> findAll();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "reduction", column = "reduction")
    })
    @Select("SELECT * FROM Regions WHERE id = #{id}")
    Optional<Region> findById(Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO Regions (name, reduction) VALUES (#{name}, #{reduction})")
    void save(Region region);

    @Update("UPDATE Regions SET name = #{name}, reduction = #{reduction} WHERE id = #{id}")
    int update(Region region);

    @Delete("DELETE FROM Regions WHERE id = #{id}")
    int delete(Long id);

    @Delete("DELETE FROM Regions")
    void deleteAll();
}

