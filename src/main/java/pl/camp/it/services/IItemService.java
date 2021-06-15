package pl.camp.it.services;

import pl.camp.it.model.Item;

import java.util.List;

public interface IItemService {
    boolean addItem(Item item);
    Item findItemByCode(String code);
    boolean updateItem(String code, Item item);
    List<Item> getItemsWithFilter();
}
