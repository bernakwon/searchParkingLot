package com.berna.global.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hrkwon
 * @className MainController
 *
 */
@Controller
public class MainController {

	/*index 페이지로 리턴*/
	@GetMapping("/")
	public String index() {
		return "index";
	}

/*	*//* 라우터 redirect*//*
	@GetMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}*/

}
