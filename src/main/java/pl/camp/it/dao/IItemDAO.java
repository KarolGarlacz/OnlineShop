package pl.camp.it.dao;

import pl.camp.it.model.Item;

import java.util.List;

public interface IItemDAO {
    List<Item> getAllItems();
    List<Item> getFilteredItems(String pattern);
    void addItem(Item item);
    Item findItemsByCode(String code);
    void updateItem(Item item);
    Item getItemById(int itemId);
}
