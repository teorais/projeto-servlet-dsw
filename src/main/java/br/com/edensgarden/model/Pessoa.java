package br.com.edensgarden.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity // Diz ao Hibernate que isso é uma tabela no banco
@Table(name = "pessoas") // Define o nome da tabela no PostgreSQL
public class Pessoa {

    @Id // Define que este campo é a Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco para gerar o ID automaticamente (Auto-Increment)
    private Long id; // Mudamos de String para Long para seguir o padrão relacional

    @Column(nullable = false) // O nome é obrigatório
    private String name;

    @Column(nullable = false, unique = true) // O email é obrigatório e não pode repetir
    private String email;

    // CONSTRUTOR VAZIO: OBRIGATÓRIO para o Hibernate
    public Pessoa() {
    }

    // Construtor para criarmos novas pessoas (sem ID, pois o banco gera)
    public Pessoa(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}