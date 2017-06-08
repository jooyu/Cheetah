package org.yujoo.baas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/**
	 * Index
	 * @param model
	 * @param request
	 */
	@RequestMapping(value={"","/","index"})
	public String index( HttpServletRequest request) {
		System.out.println("!!!!!!!!!!!!");
		return "hello";
	}
	

}