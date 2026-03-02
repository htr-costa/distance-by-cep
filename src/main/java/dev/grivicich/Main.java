package dev.grivicich;

import dev.grivicich.dto.AddressDto;
import dev.grivicich.dto.GeoDto;
import dev.grivicich.services.GeoService;
import dev.grivicich.services.ViaCepService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ViaCepService viaCepService = new ViaCepService();
        GeoService geoService = new GeoService();

        Scanner in = new Scanner(System.in);
        System.out.print("Informe o CEP: ");
        String cep = in.nextLine();

        try {
            AddressDto address = viaCepService.getAddress(cep); // random-generated cep for testing

            System.out.println("Endereço encontrado:");
            System.out.println(address);

            GeoDto geo = geoService.getCoords(address);

            System.out.println("\nCoordenadas:");
            System.out.println(geo);
        } catch (Exception e) {
            System.out.println("Erro ao buscar informacoes do CEP informado.");
        }
    }
}