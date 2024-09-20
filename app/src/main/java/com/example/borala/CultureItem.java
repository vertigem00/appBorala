package com.example.borala;

public class CultureItem {
    private String title;
    private int imageResId;

    public CultureItem(String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }
}
