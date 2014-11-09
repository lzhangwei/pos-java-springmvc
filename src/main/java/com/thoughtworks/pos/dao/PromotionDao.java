package com.thoughtworks.pos.dao;

import com.thoughtworks.pos.model.Promotion;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PromotionDao {
    public Promotion getPromotionById(int id);
    public List<Promotion> getPromotionsByItemId(int id);
    public int getPromotionDiscount(int id);
}
