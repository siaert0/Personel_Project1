package com.project.Boot4;


import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

	@Autowired
	private QuestionRepository qRepository;
	
	@GetMapping("/")
	public String index(){
		return "redirect:list";
	}
	
	@GetMapping("/list")
	public String Listindex(Model model){
		model.addAttribute("qList", qRepository.findAll());
		return "index";
	}
	
}
