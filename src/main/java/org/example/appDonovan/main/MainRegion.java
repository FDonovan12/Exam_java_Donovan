package org.example.appDonovan.main;

import org.example.appDonovan.entity.Region;
import org.example.appDonovan.repository.RegionRepository;


public class MainRegion {
    public static void main(String[] args) {
        Region region = new Region(1);
        System.out.println("findPopulationByRegion");
        System.out.println(RegionRepository.getRepository().findPopulationByRegion(region));

        System.out.println("findCitiesByRegion");
        RegionRepository.getRepository().findCitiesByRegion(region).forEach(System.out::println);
    }
}
