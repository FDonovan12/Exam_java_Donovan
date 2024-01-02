package org.example.appDonovan.main;

import org.example.appDonovan.entity.Department;
import org.example.appDonovan.entity.Region;
import org.example.appDonovan.repository.DepartmentRepository;


public class MainDepartement {
    public static void main(String[] args) {
        Region region = new Region(1);
        System.out.println("findDepartmentsByRegion");
        DepartmentRepository.getRepository().findDepartmentsByRegion(region).forEach(System.out::println);
        Department department = new Department(1);
        System.out.println("findPopulationByDepartment");
        System.out.println(DepartmentRepository.getRepository().findPopulationByDepartment(department));
        ;
    }
}
