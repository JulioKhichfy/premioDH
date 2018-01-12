package org.ebook_premio.models;

import java.math.BigDecimal;

public class CarrinhoItem {

    private TipoPreco tipoPreco;
    private Trabalho trabalho;

    public CarrinhoItem(Trabalho trabalho, TipoPreco tipoPreco) {
        this.trabalho = trabalho;
        this.tipoPreco = tipoPreco;
    }

    public TipoPreco getTipoPreco() {
        return tipoPreco;
    }

    public void setTipoPreco(TipoPreco tipoPreco) {
        this.tipoPreco = tipoPreco;
    }

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}
	
	public BigDecimal getPreco(){
	    return trabalho.precoPara(tipoPreco);
	}

	public BigDecimal getTotal(int quantidade) {
	    return this.getPreco().multiply(new BigDecimal(quantidade));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipoPreco == null) ? 0 : tipoPreco.hashCode());
		result = prime * result + ((trabalho == null) ? 0 : trabalho.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (tipoPreco != other.tipoPreco)
			return false;
		if (trabalho == null) {
			if (other.trabalho != null)
				return false;
		} else if (!trabalho.equals(other.trabalho))
			return false;
		return true;
	}
}
   