package br.com.alura.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttpConfiguration {
    private HttpClient client = HttpClient.newHttpClient();
    private static ClientHttpConfiguration instance = null;

    private ClientHttpConfiguration() {}

    public HttpResponse<String> dispararRequisicaoGet(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public <B> HttpResponse<String> dispararRequisicaoPost(String uri, B body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(new Gson().toJson(body)))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static ClientHttpConfiguration getInstance() {
        if(instance == null) instance = new ClientHttpConfiguration();

        return instance;
    }
}
