package com.thoughtworks.pos.model;

public class PromotionTwoForOne extends Promotion {
    @Override
    public double caculatePromotionPrice(CartItem cartItem) {
        return (int)cartItem.getNum() / 3 * cartItem.getPrice();
    }
}
