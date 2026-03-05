package dev.grivicich.services;

import dev.grivicich.dto.AddressDto;
import dev.grivicich.dto.GeoDto;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class GeoService {

    public GeoDto getCoords(AddressDto address) throws Exception {

        String query = address.getLogradouro() + ", "
                + address.getLocalidade() + ", "
                + address.getUf() + ", Brazil";

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://nominatim.openstreetmap.org/search?q="
                                + encodedQuery + "&format=json&limit=1"))
                .header("User-Agent", "Java Application")
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        GeoDto[] geo = mapper.readValue(response.body(), GeoDto[].class);

        if (geo.length == 0) {
            throw new RuntimeException("Coordenadas não encontradas para o endereço.");
        }

        return geo[0];
    }

    public double calculateDistance(GeoDto point1, GeoDto point2) {
        final int R = 6371;

        double lat1 = Double.parseDouble(point1.getLat());
        double lon1 = Double.parseDouble(point1.getLon());
        double lat2 = Double.parseDouble(point2.getLat());
        double lon2 = Double.parseDouble(point2.getLon());

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; 
    }
}