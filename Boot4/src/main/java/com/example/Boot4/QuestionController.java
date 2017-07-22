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
		QuestionVO qVO = new QuestionVO(user,title,contents); 
		qRepository.save(qVO);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id,Model model){
		model.addAttribute("show", qRepository.findOne(id));
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id,Model model,HttpSession session){
		try{
			QuestionVO qVO = qRepository.findOne(id);
			hasPermission(session, qVO);
			model.addAttribute("qVO",qVO); 
			return "qna/updateform";
		}catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id,String title, String contents,HttpSession session,Model model){
		try{
			QuestionVO qVO = qRepository.findOne(id);
			hasPermission(session, qVO);
			qVO.update(title,contents); // id를 통해 테이블에서 찾아온 객체의 값을 this.title 과 this.contents를 통해 바꿔준다.
			qRepository.save(qVO); 
			return String.format("redirect:/question/%d", id);
		}catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
		 
	}
	
	@DeleteMapping("/{id}")
	public String dalete(@PathVariable Long id,Model model,HttpSession session){
		try{
			QuestionVO qVO = qRepository.findOne(id);
			hasPermission(session, qVO);
			qRepository.delete(id);
			return "redirect:/";
		}catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
	}
	
	
	
	private boolean hasPermission(HttpSession session,QuestionVO question){
		if(!HttpSessionUtils.isLoginUser(session)){
			throw new IllegalStateException("로그인이 필요합니다!");
		}
		
		UserVO loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameUser(loginUser)){
			throw new IllegalStateException("자신이 쓴 글만 수정,삭제가 가능합니다!");
		}
		return true;
	}
	
	
}
