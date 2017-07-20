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
		QuestionVO qVO = new QuestionVO(user,title,contents);  // UserVO가 들어가는 이유는 
		qRepository.save(qVO);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id,Model model){
		model.addAttribute("show", qRepository.findOne(id));
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id,Model model){
		model.addAttribute("update", qRepository.findOne(id));
		return "/qna/updateform";	
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id,String title, String contents){
		QuestionVO qVO = qRepository.findOne(id);
		qVO.update(title,contents); // id를 통해 테이블에서 찾아온 객체의 값을 this.title 과 this.contents를 통해 바꿔준다.
		qRepository.save(qVO); 
		return String.format("redirect:/question/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String dalete(@PathVariable Long id){
		qRepository.delete(id);
		return "redirect:/";
	}
	
	
}
