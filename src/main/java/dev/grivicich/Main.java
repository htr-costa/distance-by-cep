package dev.grivicich;

import dev.grivicich.dto.AddressDto;
import dev.grivicich.dto.GeoDto;
import dev.grivicich.services.GeoService;
import dev.grivicich.services.ViaCepService;

public class Main {

    public static void main(String[] args) {

        ViaCepService viaCepService = new ViaCepService();
        GeoService geoService = new GeoService();

        try {
            AddressDto address = viaCepService.getAddress("73754-038"); // random-generated cep for testing

            System.out.println("Endereço encontrado:");
            System.out.println(address);

            GeoDto geo = geoService.getCoords(address);

            System.out.println("\nCoordenadas:");
            System.out.println(geo);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}