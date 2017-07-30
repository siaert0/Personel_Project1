package com.project.Boot4;


import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.data.web.*;
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
	public String Listindex(Model model,
			 @PageableDefault(sort = { "id" }, direction = Direction.ASC, size = 3) Pageable pageble){
		Page<QuestionVO> postPage = qRepository.findAll(pageble);
		model.addAttribute("qList", postPage);
		return "index";
	}
	
}
