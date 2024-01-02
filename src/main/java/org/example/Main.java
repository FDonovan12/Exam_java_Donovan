package org.example;

import org.example.appDonovan.entity.Digimon;
import org.example.appDonovan.service.DigimonApiService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DigimonApiService das = new DigimonApiService();
        das.getDigimons().forEach(System.out::println);
    }
}