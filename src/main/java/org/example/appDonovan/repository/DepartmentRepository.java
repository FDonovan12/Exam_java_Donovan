package org.example.appDonovan.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.appDonovan.entity.Department;
import org.example.appDonovan.entity.Region;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class DepartmentRepository extends AbstractRepository <Department>{
    private static DepartmentRepository departmentRepository;
    private DepartmentRepository() {
        tableName = Department.class.getSimpleName().toLowerCase();
    }
    public static DepartmentRepository getRepository() {
        if (departmentRepository == null) {
            departmentRepository = new DepartmentRepository();
        }
        return departmentRepository;
    }

    protected Department getObject(ResultSet resultSet) {
        try {
            Department department = new Department();
            department.setId(resultSet.getInt("id"));
            department.setCode(resultSet.getString("code"));
            department.setName(resultSet.getString("name"));
            Map<String, Object> mapFind = new HashMap<>();
            mapFind.put("id", resultSet.getLong("region_id"));
            department.setRegion(RegionRepository.getRepository().findOneBy(mapFind));
            return department;
        } catch (Exception ignored) {}
        return null;
    }

    public List<Department> findDepartmentsByRegion(Region region) {
        Map<String, Object> mapFind = new HashMap<>();
        mapFind.put("region_id", region.getId());
        return DepartmentRepository.getRepository().findBy(mapFind);
    }

    public int findPopulationByDepartment(Department department) {
        AtomicInteger sum = new AtomicInteger();
        CityRepository.getRepository().findCitiesByDepartment(department)
                .forEach(city -> sum.addAndGet(city.getPopulation()));
        return sum.get();
    }
    protected String insert(Department object) {
        return "";
    }
    protected String update(Department object) {
        return "";
    }
}
