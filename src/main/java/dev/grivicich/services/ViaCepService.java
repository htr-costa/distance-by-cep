package dev.grivicich.services;

import dev.grivicich.dto.AdressDto;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService {

    AdressDto adress = new AdressDto();

    public AdressDto getAdress(String cep) throws IOException, InterruptedException {
        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/")).build();
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            adress = mapper.readValue(response.body(), AdressDto.class);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return adress;
    }



}
