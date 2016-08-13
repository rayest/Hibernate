package cn.ipay.model;

import cn.ipay.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Rayest on 2016/8/13 0013.
 */
public class TeacherTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void after() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testAddTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("Pay");
        session.save(teacher);
    }

    @Test
    public void testDeleteTeacher() {
        Teacher teacher = (Teacher) session.get(Teacher.class, Long.valueOf(1));
        session.delete(teacher);
    }

    @Test
    public void testUpdateTeacher() {
        Teacher teacher = (Teacher) session.get(Teacher.class, Long.valueOf(2));
        teacher.setName("Candy");
        session.update(teacher);
    }

    @Test
    public void testListTeacher(){
        String hql = "from Teacher";
        Query query = session.createQuery(hql);
        List<Teacher> teachers = query.list();
        for (Teacher teacher : teachers){
            System.out.println(teacher);
        }
    }

}
