package service;


import model.Orders;
import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void createOrder(Orders orders);

    public Optional<Orders> getOrderUser(User user);

    List<Product> getBasket(User user);

    void confirmOrder(User user);
}
