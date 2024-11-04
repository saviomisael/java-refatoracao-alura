package br.com.alura.domain;

import java.util.ArrayList;

public class Abrigo {
    private String nome, telefone, email;
    private Long id;
    private ArrayList<Pet> pets;

    public Abrigo(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.pets = new ArrayList<>();
    }

    public Abrigo() {}

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Abrigo{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
