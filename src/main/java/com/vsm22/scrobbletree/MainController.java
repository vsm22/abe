package com.vsm22.scrobbletree;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value="/")
	public String index() {
		return "index.html";
	}
}
