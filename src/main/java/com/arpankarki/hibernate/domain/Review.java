package com.arpankarki.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String comment;

	@ManyToOne()
	private Course course;

	public Review() {
		super();
	}

	public Review(String comment) {
		super();
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", comment=" + comment + ", course=" + course + "]";
	}

}
