package com.thoughtworks.pos.util;

import com.thoughtworks.pos.model.Promotion;
import com.thoughtworks.pos.model.PromotionDiscount;
import com.thoughtworks.pos.model.PromotionSecondHalf;
import com.thoughtworks.pos.model.PromotionTwoForOne;

public class PromotionFactory {
    public static final int PROMOTION_DISCOUNT = 1;
    public static final int PROMOTION_SECOND_HALF = 2;
    public static final int PROMOTION_TWO_FOR_ONE = 3;

    public static Promotion getPromotionByType(int type){
        Promotion promotion = null;
        switch(type){
            case PROMOTION_DISCOUNT:
                promotion = new PromotionDiscount();
                break;
            case PROMOTION_SECOND_HALF:
                promotion = new PromotionSecondHalf();
                break;
            case PROMOTION_TWO_FOR_ONE:
                promotion = new PromotionTwoForOne();
                break;

        }
        return promotion;
    }
}
