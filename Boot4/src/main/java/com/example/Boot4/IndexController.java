package com.example.Boot4;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
	
		@GetMapping("")
		public String index(){
			return "index";
		}

}
