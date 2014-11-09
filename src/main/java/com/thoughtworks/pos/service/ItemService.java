package com.thoughtworks.pos.service;

import com.thoughtworks.pos.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ItemService {
    List<Item> getItems();

    Item getItemByBarcode(String barcode);
}
