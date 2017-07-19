package com.example.Boot4;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionRepository qRepository;

	@GetMapping("/form")
	public String QuestionForm(HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "user/login";
		}
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(String title,String contents,Model model,HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "user/login";
		}
		
		UserVO user = HttpSessionUtils.getUserFromSession(session);
		QuestionVO qVO = new QuestionVO(user.getUserId(),title,contents);
		qRepository.save(qVO);
		return "redirect:/";
	}
	
}
