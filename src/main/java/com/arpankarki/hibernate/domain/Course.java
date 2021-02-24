package com.arpankarki.hibernate.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String title;

	@ManyToOne()
	@JoinColumn()
	private Instructor instructor;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@OneToMany()
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	@JoinColumn(name = "course_id") // Adding this column here prevents the creation of the join table for a one to // many unidirectional relationship					
	List<Review> reviews;// This can be used here as there is a FK reference in the Review table

	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.SAVE_UPDATE})
	@JoinTable(joinColumns = @JoinColumn(name = "course_id") ,
	inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students = new ArrayList<Student>();
	
	
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReview(Review review) {
		if (reviews == null) {
			reviews = new ArrayList<Review>();
		}
		reviews.add(review);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Course(String title, Instructor instructor) {
		super();
		this.title = title;
		this.instructor = instructor;
	}

	public Course() {
		super();
	}

}
