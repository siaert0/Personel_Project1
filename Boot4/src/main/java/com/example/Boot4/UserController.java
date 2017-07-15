package com.example.Boot4;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
	
	/*private List<UserVO> users = new ArrayList<>();*/  															
	/**
	 * 위 컬렉션은 필요가 없어진다. 
	 * H2데이터베이스에 데이터를 저장하기 때문에 
	 */
	@Autowired
	private UservoRepository uservoRepository;
	
	
	@PostMapping("")
	public String userJoin(UserVO user){
		uservoRepository.save(user);
		return "redirect:/users"; // 밑에 매소드 맵핑 주소로 쓰면 안된다. 현 주소가 /user/create이기 떄문에
								// /user/list를 쓰게 되면 /user/user/list가 되어 버린다.
	}

	
	@GetMapping("")
	public String userList(Model model){
		model.addAttribute("users",uservoRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("/form")
	public String form(){
		return "user/form";
	}
	
	
}
