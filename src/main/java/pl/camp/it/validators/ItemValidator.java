package pl.camp.it.validators;

import pl.camp.it.model.Item;

public class ItemValidator {
    public static boolean validateBasics(Item item){
        if(item.getCode().equals("") || item.getQuantity() == null || item.getQuantity() < 0){
            return false;
        }
        return true;
    }

    public static boolean validateFull(Item item){
        if(item.getName().equals("") || item.getCategory().equals("") || item.getPrice() == null || item.getPrice() < 0){
            return false;
        }
        return true;
    }
}
