package org.example.appDonovan.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.appDonovan.entity.City;
import org.example.appDonovan.entity.Department;
import org.example.appDonovan.entity.Region;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RegionRepository extends AbstractRepository <Region>{
    private static RegionRepository regionRepository;
    private RegionRepository() {
        tableName = Region.class.getSimpleName().toLowerCase();
    }
    public static RegionRepository getRepository() {
        if (regionRepository == null) {
            regionRepository = new RegionRepository();
        }
        return regionRepository;
    }

    protected Region getObject(ResultSet resultSet) {
        try {
            Region region = new Region();
            region.setId(resultSet.getInt("id"));
            region.setCode(resultSet.getString("code"));
            region.setName(resultSet.getString("name"));
            return region;
        } catch (Exception ignored) {}
        return null;
    }

    public int findPopulationByRegion(Region region) {
        List<Department> departments = DepartmentRepository.getRepository().findDepartmentsByRegion(region);
        int sum = 0;
        for (Department department : departments) {
            sum+=DepartmentRepository.getRepository().findPopulationByDepartment(department);
        }
        return sum;
    }

    public List<City> findCitiesByRegion(Region region) {
        List<Department> departments = DepartmentRepository.getRepository().findDepartmentsByRegion(region);
        List<City> cities = new ArrayList<>();
        for (Department department : departments) {
            cities.addAll(CityRepository.getRepository().findCitiesByDepartment(department));
        }
        return cities;
    }

    protected String insert(Region object) {
        return "";
    }
    protected String update(Region object) {
        return "";
    }
}
