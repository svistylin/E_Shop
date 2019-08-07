package dao.impl.hibernate;

import dao.UserDao;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserHibernateDao implements UserDao {

    SessionFactory sessionFactory;

    @Autowired
    public UserHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> from_user = sessionFactory.getCurrentSession().createQuery("FROM User");
        List<User> resultList = from_user.getResultList();
        return resultList;
    }

    @Override
    public Optional<User> check(User user) {
        TypedQuery<User> getUser = sessionFactory.getCurrentSession()
                .createQuery("from User u where u.email =:email");
        getUser.setParameter("email", user.getEmail());
        User user1 = ((Query<User>) getUser).uniqueResult();
       return Optional.ofNullable(user1);
    }

    @Override
    public void change(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void change(int id, User user) {
        TypedQuery<User> getUser = sessionFactory.getCurrentSession()
                .createQuery("from User u where u.id =:id");
        getUser.setParameter("id",id);
        User user1 = ((Query<User>) getUser).uniqueResult();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setRole(user.getRole());
        sessionFactory.getCurrentSession().update(user1);
    }

    @Override
    public void deleteUser(int id) {
        TypedQuery<User> getUser = sessionFactory.getCurrentSession()
                .createQuery("from User u where u.id =:id");
        getUser.setParameter("id",id);
        User user = ((Query<User>) getUser).uniqueResult();
        sessionFactory.getCurrentSession().delete(user);
    }
}
