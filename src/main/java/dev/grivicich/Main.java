package dev.grivicich;

import dev.grivicich.dto.AddressDto;
import dev.grivicich.dto.GeoDto;
import dev.grivicich.services.GeoService;
import dev.grivicich.services.ViaCepService;
import java.util.Scanner;

public class Main {

    private static final boolean DEBUG = true;

    public static void main(String[] args) {

        ViaCepService viaCepService = new ViaCepService();
        GeoService geoService = new GeoService();

        Scanner in = new Scanner(System.in);
        System.out.print("Informe o primeiro CEP: ");
        String firstCep = in.nextLine();
        System.out.println("Informe o segundo CEP: ");
        String secondCep = in.nextLine();

        try {
            AddressDto address1 = viaCepService.getAddress(firstCep);

            if (DEBUG) {
                System.out.println("Endereço encontrado:");
                System.out.println(address1);
            }

            AddressDto address2 = viaCepService.getAddress(secondCep);

            if (DEBUG) {
                System.out.println("Endereço encontrado:");
                System.out.println(address2);
            }

            GeoDto geo1 = geoService.getCoords(address1);
            GeoDto geo2 = geoService.getCoords(address2);

            if (DEBUG) {
                System.out.println("\nCoordenadas:");
                System.out.println(geo1);
                System.out.println(geo2);
            }

            System.out.println("Distância de Vôo de Pássaro:");
            double distance = geoService.calculateDistance(geo1, geo2);
            System.out.println(String.format("%.2f km", distance));


        } catch (Exception e) {
            System.out.println("Erro ao buscar informacoes do CEP informado.");
        }

        in.close();
    }
}