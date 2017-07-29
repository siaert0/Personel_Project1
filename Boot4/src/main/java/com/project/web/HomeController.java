package com.project.web;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.project.domain.*;

@Controller
public class HomeController {

	@Autowired
	private QuestionRepository qRepository;
	
	@GetMapping("")
	public String index(Model model){
		model.addAttribute("qList", qRepository.findAll());
		return "index";
	}
	
}
