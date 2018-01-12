package org.ebook_premio.controller;

import java.util.concurrent.Callable;

import org.ebook_premio.models.CarrinhoCompras;
import org.ebook_premio.models.DadosPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/pagamento")
@Controller

/**
 * poderia declarar aqui SCOPE_REQUEST, para o carrinho ser visto pelo pagamento Controller ?
 * @author julio
 *
 */
//@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired 
	CarrinhoCompras carrinho;
    
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model){
	    return () -> {
	        try {
	            String uri = "http://book-payment.herokuapp.com/payment";
	            String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
	            model.addFlashAttribute("message", response);
	            System.out.println(response);
	            return new ModelAndView("redirect:/trabalhos");
	        } catch (HttpClientErrorException e) {
	            e.printStackTrace();
	            model.addFlashAttribute("message", "Valor maior que o permitido");
	            return new ModelAndView("redirect:/trabalhos");
	        }
	    };
	}
}
