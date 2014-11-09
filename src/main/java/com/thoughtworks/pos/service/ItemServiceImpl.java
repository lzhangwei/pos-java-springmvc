package com.thoughtworks.pos.service;

import com.thoughtworks.pos.dao.CategoryDao;
import com.thoughtworks.pos.dao.ItemDao;
import com.thoughtworks.pos.dao.PromotionDao;
import com.thoughtworks.pos.model.Category;
import com.thoughtworks.pos.model.Item;
import com.thoughtworks.pos.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private PromotionDao promotionDao;

    @Override
    public List<Item> getItems() {
        List<Item> items = itemDao.getItems();
        for(Item item : items) {
            Category category = categoryDao.getCategoryById(item.getCategoryId());
            item.setCategory(category);

            List<Promotion> promotionList = promotionDao.getPromotionsByItemId(item.getId());
            item.setPromotionList(promotionList);

            item.setDiscount(promotionDao.getPromotionDiscount(item.getId()));
        }
        return items;
    }

    @Override
    public Item getItemByBarcode(String barcode) {
        Item item = itemDao.getItemByBarcode(barcode);
        item.setCategory(categoryDao.getCategoryById(item.getCategoryId()));
        item.setPromotionList(promotionDao.getPromotionsByItemId(item.getId()));
        item.setDiscount(promotionDao.getPromotionDiscount(item.getId()));
        return item;
    }
}
