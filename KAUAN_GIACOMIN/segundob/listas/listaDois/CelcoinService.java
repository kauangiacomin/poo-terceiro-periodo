package KAUAN_GIACOMIN.segundob.listas.listaDois;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CelcoinService {
    private final JsonUtils jsonUtils;

    public CelcoinService() {
        this.jsonUtils = new JsonUtils();
    }

    public String generateToken() {
        String body = "-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"client_id\"\r\n\r\n41b44ab9a56440.teste.celcoinapi.v5\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"grant_type\"\r\n\r\nclient_credentials\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"client_secret\"\r\n\r\ne9d15cde33024c1494de7480e69b7a18c09d7cd25a8446839b3be82a56a044a3\r\n-----011000010111000001101001--";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.openfinance.celcoin.dev/v5/token"))
                .header("accept", "application/json")
                .header("content-type", "multipart/form-data; boundary=---011000010111000001101001")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return jsonUtils.toMap(response.body()).get("access_token").toString();
    }

    public String listAgreements(String token) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.openfinance.celcoin.dev/v5/transactions/institutions?Type=ESTADUAL&UF=AC"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String consultBill(String token, String digitable) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.openfinance.celcoin.dev/v5/transactions/billpayments/authorize"))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"barCode\":{\"type\":0,\"digitable\":\"" + digitable + "\"}}"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
