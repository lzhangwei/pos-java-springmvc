package com.thoughtworks.pos.model;

import java.util.List;

public class Item {
    private int id;
    private String barcode;
    private String name;
    private String unit;
    private double price;
    private Category category;
    private int discount;
    private List<Promotion> promotionList;

    public Item() {
    }

    public Item(int id, String barcode, String name, String unit, double price, Category category, int discount, List<Promotion> promotionList) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.promotionList = promotionList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public int getCategoryId() {
        return category.getId();
    }

    public int getId() {
        return this.id;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getDiscount() {
        return discount;
    }
}
