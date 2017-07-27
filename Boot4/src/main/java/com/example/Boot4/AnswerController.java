package com.example.Boot4;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


@RestController  
@RequestMapping("/api/question/{qId}/answers")
public class AnswerController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	/*@PostMapping("")
	public String create(@PathVariable Long qId,String contents,HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "user/login";
		}
		
		QuestionVO qVO = questionRepository.findOne(qId);
		UserVO loginUser = HttpSessionUtils.getUserFromSession(session);
		AnswerVO answerVO = new AnswerVO(loginUser, contents, qVO);
		
		answerRepository.save(answerVO);
		return String.format("redirect:/question/%d", qId);
		
	}*/ // ajax를 사용하기 때문에 위에 코드를 수정해야 한다. 밑 쪽이 수정된 코드 비교된 걸 확인하고
		// 위쪽 컨트롤러 애노테이션을 restControllor로 수정해 주어야 한다. 
	
	@PostMapping("")
	public AnswerVO create(@PathVariable Long qId,String contents,HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return null;
		}
		
		QuestionVO qVO = questionRepository.findOne(qId);
		UserVO loginUser = HttpSessionUtils.getUserFromSession(session);
		AnswerVO answerVO = new AnswerVO(loginUser, contents, qVO);
		
		return answerRepository.save(answerVO);
		
	}

	
}
