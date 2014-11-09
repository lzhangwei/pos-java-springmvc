package com.thoughtworks.pos.model;

public abstract class Promotion {
    private int  id;
    private String promotionDesc;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    protected Promotion() {
    }

    public abstract double caculatePromotionPrice(CartItem cartItem);
}
