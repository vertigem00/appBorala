package com.example.borala;

import java.io.Serializable;
import java.util.List;

public class TourismPackage implements Serializable {
    private int imageResId;
    private String title;
    private String details; // Novo campo para detalhes
    private List<Attraction> attractions;

    // Construtor
    public TourismPackage(int imageResId, String title, String details, List<Attraction> attractions) {
        this.imageResId = imageResId;
        this.title = title;
        this.details = details; // Inicializa o novo campo
        this.attractions = attractions;
    }

    // Getters
    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() { // Novo método getter para detalhes
        return details;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    // Setters
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) { // Novo método setter para detalhes
        this.details = details;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
