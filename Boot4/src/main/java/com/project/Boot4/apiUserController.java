package com.project.Boot4;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/api/users")
public class apiUserController {
	
	@Autowired
	private UservoRepository uservoRepository;
	
	@GetMapping("/{id}")
	public UserVO show(@PathVariable Long id){
		return uservoRepository.findOne(id);
	}
	 
}
