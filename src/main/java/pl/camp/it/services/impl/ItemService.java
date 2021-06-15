package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.dao.IItemDAO;
import pl.camp.it.model.Item;
import pl.camp.it.services.IItemService;
import pl.camp.it.session.SessionObject;
import pl.camp.it.validators.ItemValidator;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ItemService implements IItemService {

    @Autowired
    IItemDAO itemDAO;

    @Resource
    SessionObject sessionObject;

    public boolean addItem(Item item){
        if(!ItemValidator.validateBasics(item)){
            this.sessionObject.setInfo("Nieprawidłowy kod lub ilość");
            return false;
        }
        Item itemFromDatabase = this.itemDAO.findItemsByCode(item.getCode());
        if(itemFromDatabase != null){
            itemFromDatabase.setQuantity(itemFromDatabase.getQuantity() + item.getQuantity());
            this.itemDAO.updateItem(itemFromDatabase);
        }else{
            if(!ItemValidator.validateFull(item)){
                this.sessionObject.setInfo("Produkt o podanym kodzie nie istnieje");
                return false;
            }
            this.itemDAO.addItem(item);
        }
        return true;
    }

    public Item findItemByCode(String code){
        return this.itemDAO.findItemsByCode(code);
    }
    public boolean updateItem(String code, Item item){
        Item itemFromDb = itemDAO.findItemsByCode(code);
        if(itemFromDb == null) {
            return false;
        }

        itemFromDb.setName(item.getName());
        itemFromDb.setPrice(item.getPrice());
        itemFromDb.setQuantity(item.getQuantity());
        itemFromDb.setCode(item.getCode());
        itemFromDb.setCategory(item.getCategory());

        this.itemDAO.updateItem(itemFromDb);

        return true;
    }
    public List<Item> getItemsWithFilter(){
        if(this.sessionObject.getFindPattern() != null && !this.sessionObject.getFindPattern().equals("")){
            return this.itemDAO.getFilteredItems(this.sessionObject.getFindPattern());
        }else{
            return this.itemDAO.getAllItems();
        }
    }
}
