package com.student.studentportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.student.studentportal.binding.SearchCriteria;
import com.student.studentportal.entity.StudentEnq;
import com.student.studentportal.repository.EnquiryRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	EnquiryRepo enquiryRepo;

	@Override
	public Boolean addEnq(StudentEnq se) {
		StudentEnq savedEnq = enquiryRepo.save(se);
		return savedEnq.getCid() != null;
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria sc) {

		StudentEnq enq = new StudentEnq();
		enq.setCid(cid);

		if (sc.getCourseMode() != null && !sc.getCourseMode().equals("")) {
			enq.setCourseMode(sc.getCourseMode());
		}

		if (sc.getCourseName() != null && !sc.getCourseName().equals("")) {
			enq.setCourseName(sc.getCourseName());
		}

		Example<StudentEnq> of = Example.of(enq);

		List<StudentEnq> allEnquiries = enquiryRepo.findAll(of);
		return allEnquiries;
	}

}
