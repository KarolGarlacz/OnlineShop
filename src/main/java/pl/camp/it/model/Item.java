package pl.camp.it.model;

public class Item {
    private String name;
    private Double price;
    private Integer quantity;
    private String code;
    private String category;



public Item(){

}

    public Item(String name, Double price, Integer quantity, String code, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.code = code;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
