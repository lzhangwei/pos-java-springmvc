package com.thoughtworks.pos.model;

import java.util.List;

public class CartItem {
    private Item item;
    private double num;
    private double sumPrice;
    private double promotionPrice;

    public CartItem(Item item, double num) {
        this.item = item;
        this.num = num;
    }

    public String getBarcode() {
        return item.getBarcode();
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public Item getItem() {
        return item;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public double getPrice() {
        return item.getPrice();
    }

    public double calculatePromotionPrice() {
        double result = 0;
        List<Promotion> promotionList = item.getPromotionList();
        if (promotionList.size() > 0) {
            result = promotionList.get(0).caculatePromotionPrice(this);
            for (int i = 1; i < promotionList.size(); i++) {
                double price = promotionList.get(i).caculatePromotionPrice(this);
                result = price > result ? price : result;
            }
        }
        return result;
    }

    public Category getCategory() {
        return item.getCategory();
    }

    public int getCategoryId() {
        return item.getCategoryId();
    }

    public int getDiscount() {
        return item.getDiscount();
    }

    public String getItemName() {
        return item.getName();
    }

    public String getItemUnit() {
        return item.getUnit();
    }
}
