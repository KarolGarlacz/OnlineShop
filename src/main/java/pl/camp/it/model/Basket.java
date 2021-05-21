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

    public void addItem(Item item) {
        for(BasketPosition basketPosition : this.basketPositions) {
            if(basketPosition.getItem().getCode().equals(item.getCode())) {
                basketPosition.setPieces(basketPosition.getPieces() + 1);
                return;
            }
        }

        BasketPosition basketPosition = new BasketPosition();
        basketPosition.setItem(item);
        basketPosition.setPieces(1);

        this.basketPositions.add(basketPosition);
    }

    public class BasketPosition {
        private Item item;
        private int pieces;

        public BasketPosition() {
        }

        public BasketPosition(Item item, int pieces) {
            this.item = item;
            this.pieces = pieces;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public int getPieces() {
            return pieces;
        }

        public void setPieces(int pieces) {
            this.pieces = pieces;
        }
    }
}