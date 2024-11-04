package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class PetService {
    private final ClientHttpConfiguration clientHttp;

    public PetService(ClientHttpConfiguration clientHttp) {
        this.clientHttp = clientHttp;
    }

    public void listarPetsPorAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
        HttpResponse<String> response = clientHttp.dispararRequisicaoGet(uri);
        int statusCode = response.statusCode();
        if (statusCode == 404 || statusCode == 500) {
            System.out.println("ID ou nome não cadastrado!");
        }
        String responseBody = response.body();

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList<Pet> pets = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        System.out.println(pets.isEmpty() ? "Não há pets cadastrados" : "Pets cadastrados:");

        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }

    public void importarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = new Scanner(System.in).nextLine();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(",");
                String tipo = campos[0];
                String nome = campos[1];
                String raca = campos[2];
                int idade = Integer.parseInt(campos[3]);
                String cor = campos[4];
                double peso = Double.parseDouble(campos[5]);

                Pet pet = new Pet(nome, raca, cor, tipo.toUpperCase(), idade, peso);

                String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
                HttpResponse<String> response = clientHttp.dispararRequisicaoPost(uri, pet);
                int statusCode = response.statusCode();
                String responseBody = response.body();
                if (statusCode == 200) {
                    System.out.println("Pet cadastrado com sucesso: " + nome);
                } else if (statusCode == 404) {
                    System.out.println("Id ou nome do abrigo não encontado!");
                    break;
                } else if (statusCode == 400 || statusCode == 500) {
                    System.out.println("Erro ao cadastrar o pet: " + nome);
                    System.out.println(responseBody);
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + nomeArquivo);

            if (reader != null) reader.close();
        }
    }
}
