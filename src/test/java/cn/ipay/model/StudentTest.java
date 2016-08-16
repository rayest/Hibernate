package cn.ipay.model;

import cn.ipay.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    @Test
    public void testSaveClazzAndStudent(){
        Clazz clazz = new Clazz();
        clazz.setName("Junior");

        Student student1 = new Student();
        student1.setName("Payest");
        Student student2 = new Student();
        student2.setName("Paris Sweety");

        clazz.getStudents().add(student1);
        clazz.getStudents().add(student2);

        session.save(clazz);

    }

    @Test
    public void getStudentsByClazz(){
        Clazz clazz = (Clazz) session.get(Clazz.class, Long.valueOf(4));
        Set<Student> students = clazz.getStudents();
        Iterator iterator = students.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testAddWithoutInverse(){
        Clazz clazz = new Clazz();
        clazz.setName("senior");
        Student student = new Student();
        student.setName("Candy");
        session.save(clazz);
        session.save(student);
    }

    @Test
    public void testAddWithInverse(){
        Clazz clazz = (Clazz) session.get(Clazz.class, Long.valueOf(4));
        Student student = (Student) session.get(Student.class, Long.valueOf(1));
        student.setClazz(clazz);
        clazz.getStudents().add(student);
    }

    // 级联删除，需要选择 cascade 属性为 delete，否则有外键关联的属性不允许删除
    @Test
    public void testDeleteClazzCascade(){
        Clazz clazz = (Clazz) session.get(Clazz.class, Long.valueOf(4));
        session.delete(clazz);
    }

}
