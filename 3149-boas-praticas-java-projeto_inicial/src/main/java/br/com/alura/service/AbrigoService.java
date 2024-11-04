package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class AbrigoService {
    private final ClientHttpConfiguration clientHttp;

    public AbrigoService(ClientHttpConfiguration clientHttp) {
        this.clientHttp = clientHttp;
    }

    public void listarAbrigo() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";
        String responseBody = clientHttp.dispararRequisicaoGet(uri).body();

        ObjectMapper objectMapper = new ObjectMapper();

//        Abrigo[] abrigos = objectMapper.readValue(responseBody, Abrigo[].class);
        ArrayList<Abrigo> abrigos = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        if(!abrigos.isEmpty()) System.out.println("Abrigos cadastrados:");
        else System.out.println("Nenhum Abrigo cadastrado");


        for (Abrigo abrigo : abrigos) {
            System.out.println(abrigo);
        }
    }

    public void cadastrarAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        Abrigo abrigo = new Abrigo(nome, telefone, email);

        String uri = "http://localhost:8080/abrigos";

        HttpResponse<String> response = clientHttp.dispararRequisicaoPost(uri, abrigo);

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }
}
