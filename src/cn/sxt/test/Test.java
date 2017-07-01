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
		//1.�½�Configuration����
		Configuration cfg=new Configuration().configure();
		//2.ͨ��Configuration�½�SessionFactory����
		//	SessionFactory sf=cfg.buildSessionFactory();�Ѿ���ʱ
		ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties()).build();// 4.3֮ǰʹ��
		//	ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		SessionFactory sf=cfg.buildSessionFactory(sr);
		//3.ͨ��SessionFactory�õ�Session
		Session session=(Session) sf.openSession();
		//4.ͨ��session����õ�Transaction����
		Transaction tr=session.beginTransaction();
		//5.��������
		Book book=new Book();
		book.setName("����");
		Category category=new Category();
		category.setName("����");
		book.setCategory(category);
		String hql="select count(b.name) from Book b";
		Query query=session.createQuery(hql);
		List<Object>list=query.list();
		for (Object str :list) {
			System.out.println(str);
		}
		//6.�ύ����
		tr.commit();
		//7.�ر�session
		session.close();
	}
}
