package cn.ipay.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * Created by Rayest on 2016/8/13 0013.
 */
public class StudentTest {
    @Test
    public void testStudent(){
        SessionFactory sessionFactory = null;
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student();
        student.setName("Ray");
        session.save(student);

        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
