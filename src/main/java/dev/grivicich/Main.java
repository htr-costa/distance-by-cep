package dev.grivicich;

import dev.grivicich.services.ViaCepService;

public class Main {
    public static void main(String[] args) {

        ViaCepService api = new ViaCepService();

        try {
            System.out.println(api.getAdress("49000513").toString()); // using a random-generated postalcode for testing
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}