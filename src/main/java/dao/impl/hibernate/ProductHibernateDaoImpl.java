package dao.impl.hibernate;

import dao.ProductDAO;
import model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductHibernateDaoImpl implements ProductDAO {

    SessionFactory sessionFactory;

    @Autowired
    public ProductHibernateDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public Optional<Product> getById(int id) {
        TypedQuery<Product> getProductQuery = sessionFactory.getCurrentSession()
                .createQuery("from Product p where p.id =:id");
        getProductQuery.setParameter("id", id);
        Product product = ((Query<Product>) getProductQuery).uniqueResult();
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> getAll() {
        TypedQuery<Product> getProductQuery = sessionFactory.getCurrentSession()
                .createQuery("from Product ");
        List<Product> list = ((Query<Product>) getProductQuery).list();
        return list;
    }

    @Override
    public void edit(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void deleteProduct(int id) {
        if (sessionFactory.getCurrentSession() == null) {
            sessionFactory.getCurrentSession();
        }
        TypedQuery<Product> getProduct = sessionFactory.getCurrentSession()
                .createQuery("from Product p where p.id =:id");
        getProduct.setParameter("id", id);
        Product product = ((Query<Product>) getProduct).uniqueResult();
        if (product != null) {
            sessionFactory.getCurrentSession().delete(product);
        }
    }
}
