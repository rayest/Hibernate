package cn.ipay.model;

import cn.ipay.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Rayest on 2016/8/17 0017.
 */
public class LoverTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void setUp() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void tearDown() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testSetSave(){
        Set<String> images = new HashSet<String>();
        images.add("image1.jpg");
        images.add("image2.jpg");
        images.add("image3.jpg");
        Lover lover = new Lover();
        lover.setImages(images);
        session.save(lover);
    }

    @Test
    public void testSetFetch(){
        Lover lover = (Lover) session.get(Lover.class, Integer.valueOf(1));
        Iterator iterator = lover.getImages().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
