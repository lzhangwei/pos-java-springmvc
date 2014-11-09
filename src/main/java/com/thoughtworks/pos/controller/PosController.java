package com.thoughtworks.pos.controller;

import com.thoughtworks.pos.util.Pos;
import com.thoughtworks.pos.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/pos")
public class PosController {
    public static final String CART_FILE = PosController.class.getClassLoader().getResource("cart.txt").getPath();
    @Autowired
    private Pos pos;

    @RequestMapping(method = RequestMethod.GET)
    public String posPrint(ModelMap model) {

        Scanner scanner = new Scanner();
        List<String> cartItemBarcodes = scanner.readFile(CART_FILE);

        pos.parseBarcode(cartItemBarcodes);

        pos.caculatePrice();
        model.addAttribute("categoryCartItems", pos.createCategoryLists());

        model.addAttribute("sumPrice", pos.getSumPrice());
        model.addAttribute("promotionPrice", pos.getPromotionPrice());
        return "pos";
    }
}
