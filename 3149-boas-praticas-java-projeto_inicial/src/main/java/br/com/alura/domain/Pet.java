package br.com.alura.domain;

public class Pet {
    private String nome, raca, cor, tipo;
    private int idade;
    private double peso;
    private Long id;

    public Pet(String nome, String raca, String cor, String tipo, int idade, double peso) {
        this.nome = nome;
        this.raca = raca;
        this.cor = cor;
        this.tipo = tipo;
        this.idade = idade;
        this.peso = peso;
    }

    public Pet() {}

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public String getCor() {
        return cor;
    }

    public int getIdade() {
        return idade;
    }

    public double getPeso() {
        return peso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", cor='" + cor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idade=" + idade +
                ", peso=" + peso +
                ", id=" + id +
                '}';
    }
}
