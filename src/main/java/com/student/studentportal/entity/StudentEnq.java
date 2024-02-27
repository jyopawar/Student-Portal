package com.student.studentportal.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class StudentEnq {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sid;
	private String name;
	private Integer phno;
	private String courseMode;
	private String courseName;
	private String enquiryStatus;
	@CreationTimestamp
	private LocalDate createdDate;
	private Integer cid;

}
