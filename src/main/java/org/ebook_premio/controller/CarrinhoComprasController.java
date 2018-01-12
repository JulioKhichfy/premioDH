package org.ebook_premio.controller;

import org.ebook_premio.daos.TrabalhoDAO;
import org.ebook_premio.models.CarrinhoCompras;
import org.ebook_premio.models.CarrinhoItem;
import org.ebook_premio.models.TipoPreco;
import org.ebook_premio.models.Trabalho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller no geral tem um papel bem definido, ele simplesmente trata a
 * requisição. Ele recebe os dados, repassa para um outro objeto e devolve uma
 * resposta. Pensando assim, podemos concluir que após a requisição ser
 * atendida, não faz sentido que o controller ainda esteja "vivo". Geralmente, o
 * escopo dos controllers é o de request. Isto significa que quando há uma
 * requisição, o controller é criado e depois, ela deixa de existir. Podemos
 * configurar isso através da anotação @Scope com o valor da constante
 * SCOPE_REQUEST da interface WebApplicationContext.
 * 
 * @author julio
 *
 */

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping("/carrinho")
public class CarrinhoComprasController {

	@Autowired
	private TrabalhoDAO trabalhoDao;
	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Integer trabalhoId, TipoPreco tipo) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(trabalhoId, tipo);
		carrinho.add(carrinhoItem);
		return modelAndView;
	}

	private CarrinhoItem criaItem(Integer trabalhoId, TipoPreco tipo) {
		Trabalho trabalho = trabalhoDao.find(trabalhoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(trabalho, tipo);
		return carrinhoItem;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("/carrinho/itens");
	}

	@RequestMapping("/remover")
	public ModelAndView remover(Integer trabalhoId, TipoPreco tipoPreco){
	    carrinho.remover(trabalhoId, tipoPreco);
	    return new ModelAndView("redirect:/carrinho");
	}
	
}
