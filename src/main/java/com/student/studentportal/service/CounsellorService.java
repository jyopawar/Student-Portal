package com.student.studentportal.service;

import com.student.studentportal.binding.DashboardResponse;
import com.student.studentportal.entity.Counsellor;

public interface CounsellorService {

	public String saveCounsellor(Counsellor c);

	public Counsellor loginCheck(String email, String pwd);

	public boolean recoverPwd(String email);

	public DashboardResponse getDashboardInfo(Integer cid);

}
