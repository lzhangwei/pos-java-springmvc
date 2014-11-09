package com.thoughtworks.pos.util;

import com.thoughtworks.pos.model.CartItem;
import com.thoughtworks.pos.model.Category;
import com.thoughtworks.pos.model.CategoryList;
import com.thoughtworks.pos.model.Item;
import com.thoughtworks.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class Pos {
    private List<Item> items;
    private List<CartItem> cartItems;
    private double sumPrice;
    private double promotionPrice;
    @Autowired
    private ItemService itemService;

    public void parseBarcode(List<String> cartItemBarcodes) {
        items = new ArrayList<Item>();
        cartItems = new ArrayList<CartItem>();
        for (String barcode : cartItemBarcodes) {
            String[] splitBarcode = barcode.split("-");
            Item item = itemService.getItemByBarcode(splitBarcode[0]);
            items.add(item);
            double num = splitBarcode.length == 1 ? 1 : Double.parseDouble(splitBarcode[1]);
            cartItems.add(new CartItem(item, num));
        }

        cartItems = mergeCartItems(cartItems);
    }

    private List<CartItem> mergeCartItems(List<CartItem> cartItems) {
        for (int i = 0; i < cartItems.size() - 1; i++) {
            for (int j = i + 1; j < cartItems.size(); j++) {
                if (cartItems.get(i).getBarcode().equals(cartItems.get(j).getBarcode())) {
                    double newNum = cartItems.get(i).getNum() + cartItems.get(j).getNum();
                    cartItems.get(i).setNum(newNum);
                    cartItems.remove(j);
                    i--;
                    break;
                }
            }
        }
        return cartItems;
    }

    public void caculatePrice() {
        sumPrice = 0;
        promotionPrice = 0;
        for (CartItem cartItem : cartItems) {
            cartItem.setSumPrice(cartItem.getNum() * cartItem.getPrice());
            cartItem.setPromotionPrice(cartItem.calculatePromotionPrice());
            sumPrice += cartItem.getSumPrice();
            promotionPrice += cartItem.getPromotionPrice();
        }
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public List<CategoryList> createCategoryLists() {
        List<CategoryList> categoryLists = new ArrayList<CategoryList>();
        for (int i = 0; i < cartItems.size(); i++) {
            Category category = cartItems.get(i).getCategory();

            CategoryList categoryList = new CategoryList();

            categoryList.setCategory(category);
            categoryList.addCartItem(cartItems.get(i));
            for(int j=i+1;j<cartItems.size();j++) {
                if(cartItems.get(j).getCategoryId() == category.getId()) {
                    categoryList.addCartItem(cartItems.get(j));
                    cartItems.remove(j);
                    j--;
                }
            }
            categoryLists.add(categoryList);
        }
        return categoryLists;
    }
}
