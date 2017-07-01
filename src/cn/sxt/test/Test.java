package cn.sxt.test;



import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import cn.sxt.pojo.Book;
import cn.sxt.pojo.Category;

public class Test {
	public static void main(String[] args) throws SerialException, SQLException {
		//1.新建Configuration对象
		Configuration cfg=new Configuration().configure();
		//2.通过Configuration新建SessionFactory对象
		//	SessionFactory sf=cfg.buildSessionFactory();已经过时
		ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties()).build();// 4.3之前使用
		//	ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		SessionFactory sf=cfg.buildSessionFactory(sr);
		//3.通过SessionFactory得到Session
		Session session=(Session) sf.openSession();
		//4.通过session对象得到Transaction对象
		Transaction tr=session.beginTransaction();
		//5.保存数据
		Book book=new Book();
		book.setName("买茶记");
		Category category=new Category();
		category.setName("名著");
		book.setCategory(category);
		String hql="select count(b.name) from Book b";
		Query query=session.createQuery(hql);
		List<Object>list=query.list();
		for (Object str :list) {
			System.out.println(str);
		}
		//6.提交事务
		tr.commit();
		//7.关闭session
		session.close();
	}
}
