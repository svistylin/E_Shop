package dao.impl.hibernate;

import dao.CodeDao;
import model.Code;
import model.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.Generator;

import javax.persistence.TypedQuery;

@Repository
public class CodeHibernateDao implements CodeDao {

    SessionFactory sessionFactory;

    @Autowired
    public CodeHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Code code) {
        code.setCode(Generator.getVerificationCode());
        sessionFactory.getCurrentSession().save(code);
    }

    @Override
    public int getCode(Orders orders) {
        TypedQuery<Code> getCode
                = sessionFactory.getCurrentSession().createQuery("FROM Code c where orders.id=:id");
        getCode.setParameter("id", orders.getId());
        Code code = ((Query<Code>) getCode).uniqueResult();
        return code.getCode();
    }
}
