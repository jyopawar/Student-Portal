package com.student.studentportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.studentportal.binding.DashboardResponse;
import com.student.studentportal.entity.Counsellor;
import com.student.studentportal.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	@Autowired
	CounsellorService counsellorService;

	// display login page
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "loginView";
	}

	// display signup page
	@GetMapping("/register")
	public String regPage(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "register";
	}

	// display fpwd page
	@GetMapping("/forgotPwd")
	public String recoverPwdPage(Model model) {
		return "forgotPwdView";
	}

	@PostMapping("/register")
	public String handleRegistration(Counsellor c, Model model) {
		String msg = counsellorService.saveCounsellor(c);
		model.addAttribute("msg", msg);
		return "register";
	}

	@PostMapping("/login")
	public String handleLogin(Counsellor c, HttpServletRequest req, Model model) {
		Counsellor obj = counsellorService.loginCheck(c.getEmail(), c.getPwd());
		if (obj == null) {
			model.addAttribute("errMsg", "Invalid Credentials");
			return "loginView";
		}

		HttpSession session = req.getSession(true);
		session.setAttribute("CID", obj.getCid());
		return "redirect:dashboard";

	}

	@GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Object obj = session.getAttribute("CID");
		Integer cid = (Integer) obj;
		DashboardResponse dashboardInfo = counsellorService.getDashboardInfo(cid);
		model.addAttribute("dashboard", dashboardInfo);
		return "dashboardView";
	}

	@GetMapping("/recoverPwd")
	public String recoverPwd(@RequestParam String email, Model model) {

		boolean status = counsellorService.recoverPwd(email);
		if (status) {
			model.addAttribute("sMsg", "Pwd send to your email");
		} else {
			model.addAttribute("errMsg", "Invalid email");
		}
		return "forgotPwdView";

	}

}
