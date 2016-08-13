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
public class StudentTest {
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
    public void testAddStudent() {

        Clazz clazz = new Clazz();
        clazz.setName("freshman");
        session.save(clazz);

        Student student1 = new Student();
        student1.setName("ray");
        student1.setClazz(clazz);

        Student student2 = new Student();
        student2.setName("pay");
        student2.setClazz(clazz);

        session.save(student1);
        session.save(student2);

    }

    @Test
    public void testDeleteStudent() {
        Student student = (Student) session.get(Student.class, Long.valueOf(1));
        session.delete(student);
    }

    @Test
    public void testUpdateStudent() {
        Student student = (Student) session.get(Student.class, Long.valueOf(5));
        student.setName("Paris");
        session.save(student);
    }

    @Test
    public void testListStudent() {
        String hql = "from Student";
        Query query = session.createQuery(hql);
        List<Student> students = query.list();
        for (Student student : students){
            System.out.println(student);
        }
    }


}
