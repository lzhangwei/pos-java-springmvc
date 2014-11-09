package com.thoughtworks.pos.model;

public class PromotionDiscount extends Promotion {
    @Override
    public double caculatePromotionPrice(CartItem cartItem) {
        return cartItem.getNum() * cartItem.getPrice() * ((100 - cartItem.getDiscount())) / 100.0;
    }
}
