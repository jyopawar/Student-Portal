package com.student.studentportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.studentportal.entity.StudentEnq;

@Repository
public interface EnquiryRepo extends JpaRepository<StudentEnq, Integer> {

	public List<StudentEnq> findByCid(Integer cid);

}
