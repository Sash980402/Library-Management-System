package org.example.library_management_system.entity.custom;

import org.example.library_management_system.entity.SuperEntity;

public class Book implements SuperEntity {
    private int id;
    private String name;
    private String isbn;
    private double price;
    private int publisherId;
    private int mainCategoryId;


    public Book(int id, String name, String isbn, double price,  int publisherId, int mainCategoryId) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.price = price;
        this.publisherId = publisherId;
        this.mainCategoryId = mainCategoryId;
    }

    public Book() {
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }
}
