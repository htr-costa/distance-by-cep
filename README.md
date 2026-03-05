# 📍 Distance-by-CEP

![Java 21](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9.6-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

> A Java application that calculates the distance between two Brazilian postal codes (CEPs).

## About This Project

This project is a personal learning initiative to practice Java development and REST API integration. The application takes two CEPs (Brazillian Zip Codes) as input and calculates the geographical distance between them. Currently, it supports **"As the crow flies"** (straight-line) distance calculation using the Haversine formula.

The workflow is as follows:
1.  **Input**: User provides two CEPs.
2.  **Address Lookup**: The application consults the **ViaCep API** to retrieve the full address (street, neighborhood, city, state) for each CEP.
3.  **Geocoding**: It then sends these addresses to the **OpenStreetMap (Nominatim) API** to obtain the Latitude and Longitude coordinates.
4.  **Calculation**: Finally, it computes the distance between the two coordinates.

## Tech Stack

*   **Java 21**: Core programming language.
*   **Maven**: Dependency management and build tool.
*   **Jackson (3.x)**: Used for JSON parsing and serialization.
*   **Java HTTP Client**: Standard Java library used for making API requests.

## APIs Used

This project relies on the following public APIs:

### 1. [ViaCep](https://viacep.com.br/)
A free webservice for querying Brazilian ZIP codes (CEP).
*   **Purpose**: Converts a CEP into a structured address (Logradouro, Bairro, Localidade, UF).

### 2. [OpenStreetMap (Nominatim)](https://nominatim.org/)
A search engine for OpenStreetMap data.
*   **Purpose**: Performs geocoding by taking the structured address and returning geographical coordinates (Latitude and Longitude).

## 📂 Project Structure

The project follows a standard Maven directory layout:

```
distance-by-cep/
├── pom.xml                     # Maven configuration and dependencies
├── src/
│   └── main/
│       └── java/
│           └── dev/
│               └── grivicich/
│                   ├── Main.java           # Application entry point
│                   ├── dto/                # Data Transfer Objects
│                   │   ├── AddressDto.java # Maps ViaCep response
│                   │   └── GeoDto.java     # Maps Nominatim response
│                   └── services/           # API calls
│                       ├── GeoService.java # Handles Geocoding and Distance calc
│                       └── ViaCepService.java # Handles Address lookup
```

## Future Improvements

*   [ ] Implement **driving distance** calculation.
*   [ ] Improve error handling.
*   [ ] Create a simple nterface for better user interaction.
