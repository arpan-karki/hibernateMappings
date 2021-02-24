package com.arpankarki.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class InstructorDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String youtubeChannel;

	private String hobby;

	// Mapped by instructorDetail member/property in Instructor Class
	@OneToOne(mappedBy = "instructorDetail")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Instructor instructor;

	public InstructorDetail() {
		// TODO Auto-generated constructor stub
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public InstructorDetail(String youtubeChannel, String hobby) {
		super();
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

}
