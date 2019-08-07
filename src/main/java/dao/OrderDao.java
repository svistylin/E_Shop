package dao;

import model.Orders;
import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    void createOrder(Orders orders);

    Optional<Orders> getOrderByUser(User user);

    int getIdByUser(User user);

    List<Product> getBasket(User user);

    void confirmOrder(User user);

}
