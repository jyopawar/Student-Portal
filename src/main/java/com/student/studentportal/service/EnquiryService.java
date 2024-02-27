package com.student.studentportal.service;

import java.util.List;

import com.student.studentportal.binding.SearchCriteria;
import com.student.studentportal.entity.StudentEnq;

public interface EnquiryService {

	public Boolean addEnq(StudentEnq se);

	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s);

}
