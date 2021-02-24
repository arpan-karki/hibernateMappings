package com.arpankarki.hibernate.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotatedClassType;
import org.hibernate.cfg.Configuration;

public class Mappings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sessionFactory = new Configuration().configure("hibernate2.cfg.xml")
				.addPackage("com.arpankarki.hibernate.domain").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction transaction = session.beginTransaction();
			Instructor instructor = new Instructor("Arpan", "Karki", "arpnakarki@hotmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("Food", "Eating");
			instructor.setInstructorDetail(instructorDetail);
			session.save(instructor);

			Instructor instructor1 = new Instructor("Arpan", "Karki", "arpnakarki@hotmail.com");
			InstructorDetail instructorDetail2 = new InstructorDetail("Food", "Eating");
			instructor1.setInstructorDetail(instructorDetail2);
			session.save(instructor1);
			Instructor instructor2 = session.get(Instructor.class, 1);
			System.out.println(instructor2);
			// session.remove(instructor2);
			session.delete(instructor2);
			transaction.commit();

			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			InstructorDetail detail = session.get(InstructorDetail.class, 2);
			System.out.println("Tests");
			System.out.println(detail);
			Instructor instructor3 = detail.getInstructor();
			detail.getInstructor().setInstructorDetail(null);
			System.out.println(instructor3);
			session.delete(detail);
			transaction.commit();

			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Instructor instructorTemp = new Instructor("Test", "Karki", "arpnakarki@hotmail.com");
			InstructorDetail instructorDetailTemp = new InstructorDetail("Sleeping", "Eating");

			// instructorTemp.setInstructorDetail(instructorDetailTemp);
			instructorTemp.setInstructorDetail(instructorDetailTemp);

			Course course = new Course();
			course.setTitle("Guitar Lessons 43");
			Course course2 = new Course();
			course2.setTitle("Yoga Class");
			instructorTemp.addCourse(course);
			instructorTemp.addCourse(course2);
			System.out.println("***************************");
			System.out.println(instructorTemp.getCourses());
			System.out.println(instructorTemp);

			session.save(instructorTemp);
			transaction.commit();

			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Instructor tempInstructor = session.get(Instructor.class, 3);
			System.out.println(tempInstructor.getCourses());

			transaction.commit();

			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();

			Course testCourse = new Course("ames", instructorTemp);

			Review review = new Review("Loved it1");
			Review review1 = new Review("Loved it2");
			Review review2 = new Review("Loved it3");
			Review review3 = new Review("Loved it4");

			testCourse.addReview(review);
			testCourse.addReview(review1);
			testCourse.addReview(review2);
			testCourse.addReview(review3);

			session.save(testCourse);
			transaction.commit();

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			System.out.println(session.get(Course.class, 3).getReviews());
			session.close();

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			System.out.println(session.get(Course.class, 3).getReviews());
			Course tempCourse = session.get(Course.class, 3);
			// tempCourse.setInstructor(null);
			tempCourse.getInstructor().getCourses().remove(tempCourse);
			session.delete(tempCourse);
			session.getTransaction().commit();

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Student student = new Student("abc", "def", "email");
			Student student1 = new Student("abc2", "def2", "email2");
			Course testCourse1 = new Course("ames11", instructorTemp);
			Course testCourse2 = new Course("ames12", instructorTemp);
			Course testCourse3 = new Course("ames13", instructorTemp);
			testCourse1.getStudents().add(student1);
			testCourse1.getStudents().add(student);
			testCourse2.getStudents().add(student1);
			testCourse2.getStudents().add(student);
			testCourse3.getStudents().add(student1);
			testCourse3.getStudents().add(student);
			session.save(testCourse1);
			session.save(testCourse2);
			session.save(testCourse3);
			session.getTransaction().commit();

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Student student2 = session.get(Student.class, 2);
			System.out.println(student2);
			System.out.println(student2.getCourses());
			Course course3 = new Course("Gamedev", instructorTemp);
			Course course4 = new Course("Gamedev23", instructorTemp);
			student2.getCourses().add(course3);
			student2.getCourses().add(course4);
			System.out.println(student2);
			session.save(student2);
			session.getTransaction().commit();

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Student temp = session.get(Student.class, 2);
			System.out.println(temp.getCourses());
			session.close();

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Course deleteCourse = session.get(Course.class, 8);
			/*for ( Student removeStudentInCourse : deleteCourse.getStudents()) {
				removeStudentInCourse.getCourses().remove(deleteCourse);
				
			}*/
			deleteCourse.getInstructor().getCourses().remove(deleteCourse); //deleting other associations 
			session.delete(deleteCourse);
			session.getTransaction().commit();
			session.close();
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Student tobeDeleted = session.get(Student.class, 2);
			/*for (Course Course : tobeDeleted.getCourses()) {
				course.getStudents().remove(tobeDeleted);
			}*/
			session.delete(tobeDeleted);
			session.getTransaction().commit();
			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

}
