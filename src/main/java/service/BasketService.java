package service;


import model.Basket;
import model.Product;
import model.User;

import java.util.Optional;

public interface BasketService {

    void addProduct(User user, Product product);


    long getCountOfElements(User user);

    void createBasket(User user);

    public Optional<Basket> getBasketByUser(User user);

}
