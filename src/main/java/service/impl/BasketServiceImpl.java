package service.impl;

import dao.BasketDao;
import model.Basket;
import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BasketService;

import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketDao basketDao;

    @Override
    @Transactional
    public void addProduct(User user, Product product) {
        basketDao.addProduct(user, product);
    }

    @Override
    @Transactional
    public long getCountOfElements(User user) {
        return basketDao.getCountOfElements(user);
    }

    @Override
    @Transactional
    public void createBasket(User user) {
        basketDao.createBasket(user);
    }


    @Override
    @Transactional
    public Optional<Basket> getBasketByUser(User user) {
        return basketDao.getBasketByUser(user);
    }
}
