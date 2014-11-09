package com.thoughtworks.pos.model;

public class PromotionSecondHalf extends Promotion {
    @Override
    public double caculatePromotionPrice(CartItem cartItem) {
        return (int)cartItem.getNum() / 2 * (cartItem.getPrice() / 2.0);
    }
}
