package com.example.borala;

public class User {
    private String name;
    private String whatsapp;

    // Construtor vazio necessário para a deserialização do Firestore
    public User() {
    }

    // Construtor com parâmetros
    public User(String name, String whatsapp) {
        this.name = name;
        this.whatsapp = whatsapp;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
}
