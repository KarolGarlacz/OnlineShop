package pl.camp.it.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<BasketPosition> basketPositions = new ArrayList<>();


    public Basket() {

    }

    public List<BasketPosition> getBasketPositions() {
        return basketPositions;
    }

    public void setBasketPositions(List<BasketPosition> basketPositions){
        this.basketPositions = basketPositions;
    }

    public void addItem(Item item){
        for(BasketPosition basketPosition : this.basketPositions){
            if(basketPosition.getItem().getCode().equals(item.getCode())){
                basketPosition.setQuantity(basketPosition.getQuantity() + 1);
                return;
            }
        }
        BasketPosition basketPosition = new BasketPosition();
        basketPosition.setItem(item);
        basketPosition.setQuantity(1);

        this.basketPositions.add(basketPosition);
    }
}