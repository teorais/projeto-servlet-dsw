package br.com.edensgarden.model;

public class Pessoa {
    private int id;
    private String name;
    private String email;

    public Pessoa(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Pessoa(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
