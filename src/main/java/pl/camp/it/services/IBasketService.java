package pl.camp.it.services;

public interface IBasketService {
    void addItemToBasket(String code);
    double calculateBasketSum();
    void removeItemFromBasket(String code);
    void confirmOrder();
}
