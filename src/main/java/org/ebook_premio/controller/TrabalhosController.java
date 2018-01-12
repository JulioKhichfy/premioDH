package org.ebook_premio.controller;

import java.util.List;

import javax.validation.Valid;

import org.ebook_premio.daos.TrabalhoDAO;
import org.ebook_premio.infra.FileSaver;
import org.ebook_premio.models.TipoModalidadeTrabalho;
import org.ebook_premio.models.TipoPreco;
import org.ebook_premio.models.Trabalho;
import org.ebook_premio.validation.TrabalhoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/trabalhos")
public class TrabalhosController {

	@Autowired
	private TrabalhoDAO trabalhoDao;
	
	@Autowired
    private FileSaver fileSaver;

	@RequestMapping("/form")
	public ModelAndView form(@ModelAttribute("trabalho")Trabalho trabalho) {

		ModelAndView modelAndView = new ModelAndView("trabalhos/form");
		//modelAndView.addObject("tiposTrabalhos", TipoTrabalho.values());
		modelAndView.addObject("tiposModalidadeTrabalho", TipoModalidadeTrabalho.values());
		modelAndView.addObject("tiposPreco", TipoPreco.values());
		return modelAndView;
	}

	/**
	 * Note que o BindingResult vem logo após o atributo que está assinado com a
	 * anotação @Valid, essa ordem não é por acaso, precisa ser desta forma para
	 * que o Spring consiga fazer as validações da forma correta
	 * 
	 * @param trabalho
	 * @param result
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value="trabalhosHome", allEntries=true)
	public ModelAndView gravar(MultipartFile sumario, @Valid Trabalho trabalho, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return form(trabalho);
		}
		
		System.out.println(" >>>>> "+sumario.getOriginalFilename());
		
		String path = fileSaver.write("arquivos-sumario", sumario);
	    trabalho.setSumarioPath(path);

		trabalhoDao.gravar(trabalho);
		redirectAttributes.addFlashAttribute("sucesso", "Trabalho cadastrado com sucesso!");
		return new ModelAndView("redirect:trabalhos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Trabalho> trabalhos = trabalhoDao.listar();

		ModelAndView modelAndView = new ModelAndView("/trabalhos/lista");
		modelAndView.addObject("trabalhos", trabalhos);
		return modelAndView;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id){
	    ModelAndView modelAndView = new ModelAndView("/trabalhos/detalhe");
	    Trabalho trabalho = trabalhoDao.find(id);
	    modelAndView.addObject("trabalho", trabalho);
	    return modelAndView;
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Trabalho detalheJSON(@PathVariable("id") Integer id){
	    return trabalhoDao.find(id);
	}

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(new TrabalhoValidation());
	}

}
