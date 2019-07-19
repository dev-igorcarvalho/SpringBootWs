package br.com.igorcarvalhodev.springbootws.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping(path = "/")
	@ResponseBody()
	public String index() {
		return "Server status : online";
	}

}
