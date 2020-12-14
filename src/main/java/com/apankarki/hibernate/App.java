package com.apankarki.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.*;

import com.arpankarki.hibernate.domain.Student;

import antlr.collections.List;

public class App {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {
			System.out.println(session);
			System.out.println("Entered");
			Student student = new Student("firstName", "lastName", "email@email.com");
			Student student1 = new Student("firstName1", "lastName1", "email1@email.com");
			Student student2 = new Student("firstName2", "lastName2", "email2@email.com");
			Student student3 = new Student("firstName3", "lastName3", "email3@email.com");

			//Transaction transaction= session.beginTransaction();
			//session.getTransaction().begin();
			session.beginTransaction();
			session.save(student);
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.getTransaction().commit(); // hibernate automatically closes the connection after each commit
			System.out.println(session);
			
			session = sessionFactory.getCurrentSession();
			System.out.println(session);
			session.beginTransaction();
			int id = student3.getId();
			Student student4 = session.get(Student.class, id);
			System.out.println(student4);
			System.out.println(session);
			
			java.util.List<Student> studentList =  session.createQuery("from Student where id = 1").getResultList();
			System.out.println(studentList);
			System.out.println(session);
			
			
			Student testUpdateStudent=session.get(Student.class, 3);
			testUpdateStudent.setEmail("test@email.com");
			session.save(testUpdateStudent);
			session.getTransaction().commit();	
			
			
		} 
		
		finally {
			// TODO: handle finally clause
			System.out.println("Exiting");
			session.close();
			sessionFactory.close();
		}
		
	}
}
