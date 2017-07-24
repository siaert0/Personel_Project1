package com.example.Boot4;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller  
@RequestMapping("/question/{qId}/answers")
public class AnswerController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public String create(@PathVariable Long qId,String contents,HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "user/login";
		}
		
		QuestionVO qVO = questionRepository.findOne(qId);
		UserVO loginUser = HttpSessionUtils.getUserFromSession(session);
		AnswerVO answerVO = new AnswerVO(loginUser, contents, qVO);
		
		answerRepository.save(answerVO);
		return String.format("redirect:/question/%d", qId);
		
	}

	
}
