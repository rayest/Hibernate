package cn.ipay.model;

import cn.ipay.util.HibernateUtil;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Rayest on 2016/8/17 0017.
 */
public class BookTest {
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
    public void addBook() throws ParseException, IOException {
        Book book = new Book();
        book.setBookName("Java");
        book.setPrice(100);
        book.setSpecialPrice(true);
        book.setPublishDate(new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-17"));
        book.setAuthor("埃克尔");
        book.setIntroduction("简介...");

        // 插入图片
        LobHelper lobHelper = session.getLobHelper();
        InputStream in = new FileInputStream("F://javaPic//java编程思想.jpg");
        Blob bookPic = lobHelper.createBlob(in, in.available());
        book.setBookPic(bookPic);

        session.save(book);

    }


}
