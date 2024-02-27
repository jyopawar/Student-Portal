package com.student.studentportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.studentportal.entity.Counsellor;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {

	public Counsellor findByEmailAndPwd(String email, String pwd);

	public Counsellor findByEmail(String email);

}
