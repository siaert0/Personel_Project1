package com.example.Boot4;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.*;

@Controller
@RequestMapping("/users")
public class UserController {

	/* private List<UserVO> users = new ArrayList<>(); */
	/**
	 * 위 컬렉션은 필요가 없어진다. H2데이터베이스에 데이터를 저장하기 때문에
	 */

	@Autowired
	private UservoRepository uservoRepository;

	@GetMapping("/loginform")
	public String loginForm() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		UserVO userVO = uservoRepository.findByUserId(userId);

		if (userVO == null) {
			return "redirect:/users/loginform";
		}

		if (!userVO.getPassword().equals(password)) {
			return "redirect:/users/loginform";
		}

		session.setAttribute("sessionedUser", userVO);
		return "redirect:/";
	}

	@PostMapping("")
	public String userJoin(UserVO user) {
		uservoRepository.save(user);
		return "redirect:/users"; // 밑에 매소드 맵핑 주소로 쓰면 안된다. 현 주소가 /user/create이기
									// 떄문에
									// /user/list를 쓰게 되면 /user/user/list가 되어
									// 버린다.
	}

	@GetMapping("")
	public String userList(Model model) {
		model.addAttribute("users", uservoRepository.findAll());
		return "user/list";
	}

	@GetMapping("/form")
	public String form() {
		return "user/form";
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Object temUser = session.getAttribute("sessionedUser");
		if (temUser == null) {
			return "redirect:/users/loginform";
		}

		UserVO sessionedUser = (UserVO) temUser;

		if (!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("Error!!");
		}

		model.addAttribute("user", uservoRepository.findOne(id));
		return "user/updateForm";
	}

	@PutMapping("/{id}")
	public String updateUser(@PathVariable Long id, UserVO updateUser,HttpSession session) {
		Object temUser = session.getAttribute("sessionedUser");

		if (temUser == null) {
			return "redirect:/users/loginform";
		}

		UserVO sessionedUser = (UserVO) temUser;

		if (!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("Error!!");
		}
		UserVO userVO = uservoRepository.findOne(id);
		userVO.update(updateUser);
		uservoRepository.save(userVO);
		return "redirect:/users";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return "redirect:/";
	}

}
