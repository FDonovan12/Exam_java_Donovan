package org.example.appDonovan.main;

import org.example.appDonovan.entity.Department;
import org.example.appDonovan.repository.CityRepository;

public class MainCity {
    public static void main(String[] args) {
        Department department = new Department(1);
        System.out.println("findCitiesByDepartment");
        CityRepository.getRepository().findCitiesByDepartment(department).forEach(System.out::println);
    }
}
