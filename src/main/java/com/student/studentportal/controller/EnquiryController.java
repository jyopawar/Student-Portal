package com.student.studentportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.student.studentportal.binding.SearchCriteria;
import com.student.studentportal.entity.StudentEnq;
import com.student.studentportal.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	EnquiryService enquiryService;

	@GetMapping("/enquiry")
	public String enqPage(Model model) {
		model.addAttribute("enq", new StudentEnq());
		return "addEnqView";
	}

	@PostMapping("/addEnquiry")
	public String addEnquiry(@ModelAttribute("enq") StudentEnq enq, HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer obj = (Integer) session.getAttribute("CID");
		enq.setCid(obj);

		Boolean addEnq = enquiryService.addEnq(enq);
		if (addEnq) {
			model.addAttribute("succMsg", "Enquiry Added");
		} else {
			model.addAttribute("errMsg", "Enquiry Failed To Added");

		}

		return "addEnqView";
	}

	@GetMapping("/viewEnquiries")
	public String viewEnquiries(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer obj = (Integer) session.getAttribute("CID");

		// model.addAttribute("sc", new SearchCriteria());
		model.addAttribute("sc", new SearchCriteria());
		List<StudentEnq> enquiryList = enquiryService.getEnquiries(obj, new SearchCriteria());

		model.addAttribute("enquiries", enquiryList);
		return "displayViewEnquiry";
	}

	@PostMapping("/filterEnquiries")
	public String filterEnquiries(@ModelAttribute("sc") SearchCriteria sc, HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer obj = (Integer) session.getAttribute("CID");

		List<StudentEnq> enquiryLists = enquiryService.getEnquiries(obj, sc);

		model.addAttribute("enquiries", enquiryLists);
		return "displayViewEnquiry";
	}

}
