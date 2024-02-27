package com.student.studentportal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.studentportal.binding.DashboardResponse;
import com.student.studentportal.entity.Counsellor;
import com.student.studentportal.entity.StudentEnq;
import com.student.studentportal.repository.CounsellorRepo;
import com.student.studentportal.repository.EnquiryRepo;
import com.student.studentportal.util.EmailUtils;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	CounsellorRepo counsellorRepo;
	@Autowired
	EmailUtils emailUtils;
	@Autowired
	EnquiryRepo enquiryRepo;

	@Override
	public String saveCounsellor(Counsellor c) {
		Counsellor obj = counsellorRepo.findByEmail(c.getEmail());
		if (obj != null) {
			return "Duplicate Email";
		}

		Counsellor counsellorObj = counsellorRepo.save(c);
		if (counsellorObj.getCid() != null) {
			return "Registration Successful";
		}
		return "Registration Failed";
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {
		return counsellorRepo.findByEmailAndPwd(email, pwd);
	}

	@Override
	public boolean recoverPwd(String email) {
		Counsellor obj = counsellorRepo.findByEmail(email);
		if (obj == null) {
			return false;
		}

		String subject = "sending password";
		String body = "password recover:" + obj.getPwd() + "";
		return emailUtils.sendMail(subject, body, email);
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer cid) {
		List<StudentEnq> allEnq = enquiryRepo.findByCid(cid);

		int enroll = allEnq.stream().filter(a -> a.getEnquiryStatus().equals("enrolled")).collect(Collectors.toList())
				.size();

		DashboardResponse response = new DashboardResponse();
		response.setTotalEnquiries(allEnq.size());
		response.setEnrolledEnquiries(enroll);
		response.setLostEnquiries(allEnq.size() - enroll);

		return response;
	}

}
