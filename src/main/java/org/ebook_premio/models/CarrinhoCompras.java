package org.ebook_premio.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * para o pagamento "enxergar" o carrinho:
 * Passaremos para esta mesma anotação uma segunda informação definindo que o
 * Spring criará um proxy envolvendo o objeto alvo (TARGET_CLASS) afim de
 * possibilitar que as informações possam ser transitadas de um escopo para o
 * outro
 * 
 * @author julio
 *
 */

@Component
//Assim conseguiremos fazer com que o carrinho de compras seja acessivel dentro dos controllers sem ter que mudar o escopo dos controllers para isso
@Scope(value=WebApplicationContext.SCOPE_SESSION,proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	public int getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}

	/**
	 * Este código percorre toda a lista de itens e soma o valor de cada item a
	 * um aculumador. Caso não conheça a API de Stream e Lambdas, que são
	 * recursos do Java 8, recomendamos que faça o curso de java 8 disponível
	 * aqui no Alura.
	 * 
	 * @return
	 */
	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> (proximo + acumulador));
	}
	
	public Collection<CarrinhoItem> getItens() {
	    return itens.keySet();
	}
	
	public BigDecimal getTotal(CarrinhoItem item){
	    return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal(){
	    BigDecimal total = BigDecimal.ZERO;
	    for (CarrinhoItem item : itens.keySet()) {
	        total = total.add(getTotal(item));
	    }
	    return total;
	}
	
	public void remover(Integer trabalhoId, TipoPreco tipoPreco) {
	    Trabalho trabalho = new Trabalho();
	    trabalho.setId(trabalhoId);
	    itens.remove(new CarrinhoItem(trabalho, tipoPreco));
	}

}
