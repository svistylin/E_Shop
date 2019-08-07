package dao.impl.hibernate;

import dao.OrderDao;
import model.Orders;
import model.Product;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderHibernateDao implements OrderDao {

    SessionFactory sessionFactory;

    @Autowired
    public OrderHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createOrder(Orders orders) {
        sessionFactory.getCurrentSession().save(orders);
    }

    @Override
    public Optional<Orders> getOrderByUser(User user) {
        TypedQuery<Orders> query
                = sessionFactory.getCurrentSession().createQuery("from Orders where user.id =:id");
        query.setParameter("id", user.getId());
        List<Orders> list = ((Query<Orders>) query).list();
        return Optional.ofNullable(list.get(list.size() - 1));
    }

    @Override
    public int getIdByUser(User user) {
        return 0;
    }

    @Override
    public List<Product> getBasket(User user) {
        Optional<Orders> orderByUser = getOrderByUser(user);
        return orderByUser.get().getBasket().getProductList();
    }

    @Override
    public void confirmOrder(User user) {
        Optional<Orders> orderByUser = getOrderByUser(user);
        Orders order = orderByUser.get();
        order.setConfirmed(true);
        sessionFactory.getCurrentSession().update(order);
    }
}
