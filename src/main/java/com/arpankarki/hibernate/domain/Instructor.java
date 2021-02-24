package com.arpankarki.hibernate.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;

	private String lastName;

	private String email;

	@OneToMany( fetch = FetchType.EAGER
			    ,mappedBy = "instructor" )//{CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<Course> courses ;

	public Instructor() {
		// TODO Auto-generated constructor stub
	}

	// Specifying the relationship
	// Cascade is added in order to cascade the operation performed on this entity
	// onto the target entity also
	@Cascade(CascadeType.SAVE_UPDATE)
	@OneToOne()
	@JoinColumn()
	private InstructorDetail instructorDetail;

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	}

	public Instructor(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	public void addCourse(Course course) {
		if (courses == null) {
			courses = new ArrayList<Course>();
			System.out.println("NULLLLL");
		}
		System.out.println("NOT NULL ");
		course.setInstructor(this);
		courses.add(course);
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	

}
