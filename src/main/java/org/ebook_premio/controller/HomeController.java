package org.ebook_premio.controller;

import java.util.List;

import org.ebook_premio.daos.TrabalhoDAO;
import org.ebook_premio.models.Trabalho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Cacheable(value="trabalhosHome")
public class HomeController {
	
	@Autowired
	TrabalhoDAO trabalhoDao;

	@RequestMapping("/")
	public ModelAndView index(){
		
		List<Trabalho> trabalhos = trabalhoDao.listar();

		ModelAndView modelAndView = new ModelAndView("/home");
		modelAndView.addObject("trabalhos", trabalhos);
		return modelAndView;
	}
}
