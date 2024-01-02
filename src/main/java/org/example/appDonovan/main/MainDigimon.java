package org.example.appDonovan.main;

import org.example.appDonovan.service.DigimonApiService;


public class MainDigimon {
    public static void main(String[] args) {
        DigimonApiService das = new DigimonApiService();
        das.getDigimons().forEach(System.out::println);
    }
}
