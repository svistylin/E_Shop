package dao.impl.hibernate;

import dao.BasketDao;
import model.Basket;
import model.Product;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BasketHibernateDaoImpl implements BasketDao {

    private SessionFactory sessionFactory;

    @Autowired
    public BasketHibernateDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(User user, Product product) {
        Optional<Basket> basketByUser = getBasketByUser(user);
        Basket basket = null;
        if (basketByUser.isPresent()) {
            basket = basketByUser.get();
        } else {
            createBasket(user);
            basket = getBasketByUser(user).get();
        }
        Session session = sessionFactory.getCurrentSession();
        List<Product> productList = basket.getProductList();
        productList.add(product);
        basket.setProductList(productList);
        session.update(basket);
    }

    @Override
    public Optional<Basket> getBasketByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Basket basket = null;
        Query getBasketByUser = session.createQuery("FROM Basket b WHERE user.id =:id");
        getBasketByUser.setParameter("id", user.getId());
        List<Basket> list = getBasketByUser.list();
        if (list.size() != 0) {
            basket = list.get(list.size() - 1);
        }
        return Optional.ofNullable(basket);
    }

    @Override
    public long getCountOfElements(User user) {
        Optional<Basket> basketByUser = getBasketByUser(user);
        if (basketByUser.isPresent()) {
            Basket basket = basketByUser.get();
            return basket.getProductList().size();
        }
        return 0;
    }

    @Override
    public void createBasket(User user) {
        sessionFactory.getCurrentSession().save(new Basket(user, new ArrayList<>()));
    }
}
