package com.assign01.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(RegionController.class);

	//@RequestMapping(value = "/")
	public ModelAndView main(Locale locale) throws Exception
	{
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return new ModelAndView("main", "message", "환영합니다!");
	}
}
