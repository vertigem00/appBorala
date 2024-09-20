package com.example.borala;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Attraction implements Serializable {
    private int imageResId; // Recurso da imagem associada à atração
    private String title;
    private String description;
    private boolean isFavorite;
    private List<String> categories; // Ex: "Natureza", "Cultura", etc.
    private String time; // Novo campo: horário da atração no itinerário

    public Attraction(int imageResId, String title, String description, List<String> categories) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.isFavorite = false; // Por padrão, a atração não é favorita
    }

    // Construtor sem lista de categorias (para compatibilidade com outros usos)
    public Attraction(int imageResId, String title, String description, String category) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
        this.categories = List.of(category); // Adiciona a única categoria à lista
        this.isFavorite = false;
    }

    // Getters e setters...

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
