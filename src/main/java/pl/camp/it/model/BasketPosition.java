package pl.camp.it.model;

public class BasketPosition {

    private int id;
    private Item item;
    private int quantity;
    private int ItemId;

    public BasketPosition(){

    }

    public BasketPosition(int id, Item item, int quantity){

        this.id = id;
        this.item = item;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }
}
