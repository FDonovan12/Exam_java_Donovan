package org.example.appDonovan.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.appDonovan.entity.City;
import org.example.appDonovan.entity.Department;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.appDonovan.service.Color.ANSI_BACKGROUND_RED;
import static org.example.appDonovan.service.Color.ANSI_RESET;

@Getter
@Setter
public class CityRepository extends AbstractRepository <City>{
    private static CityRepository cityRepository;
    private CityRepository() {
        tableName = City.class.getSimpleName().toLowerCase();
    }
    public static CityRepository getRepository() {
        if (cityRepository == null) {
            cityRepository = new CityRepository();
        }
        return cityRepository;
    }

    protected City getObject(ResultSet resultSet) {
        try {
            City city = new City();
            city.setId(resultSet.getInt("id"));
            city.setCode(resultSet.getString("code"));
            city.setName(resultSet.getString("name"));
            city.setSiren(resultSet.getString("siren"));
            city.setPopulation(resultSet.getInt("population"));

            Map<String, Object> mapFindDepartment = new HashMap<>();
            mapFindDepartment.put("id", resultSet.getString("department_id"));
            city.setDepartment(DepartmentRepository.getRepository().findOneBy(mapFindDepartment));

            Map<String, Object> mapFindPostalCode = new HashMap<>();
            mapFindPostalCode.put("city_id", city.getId());
            city.setPostalCodeList(PostalCodeRepository.getRepository().findBy(mapFindPostalCode));
            return city;
        } catch (Exception e) {
            System.out.println(ANSI_BACKGROUND_RED + "City getObject " + e + ANSI_RESET);
        }
        return null;
    }

    public List<City> findCitiesByDepartment(Department department) {
        Map<String, Object> mapFind = new HashMap<>();
        mapFind.put("department_id", department.getId());
        return CityRepository.getRepository().findBy(mapFind);
    }
    protected String insert(City object) {
        return "";
    }
    protected String update(City object) {
        return "";
    }
}
