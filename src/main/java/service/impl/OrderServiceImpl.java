package service.impl;

import dao.OrderDao;
import model.Orders;
import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public void createOrder(Orders orders) {
        orderDao.createOrder(orders);
    }

    @Override
    @Transactional
    public Optional<Orders> getOrderUser(User user) {
        return orderDao.getOrderByUser(user);
    }

    @Override
    @Transactional
    public List<Product> getBasket(User user) {
        return orderDao.getBasket(user);
    }

    @Override
    @Transactional
    public void confirmOrder(User user) {
        orderDao.confirmOrder(user);
    }
}
