package org.ebook_premio.models;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Trabalho {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String titulo;
	private String descricao;
	private int paginas;
	
	@Enumerated(EnumType.STRING)
	private TipoModalidadeTrabalho tipoModalidadeTrabalho;
	
	/*@Enumerated(EnumType.STRING)
	private TipoPremio tipoPremio;*/
	
	@ElementCollection
	private List<Preco> precos;
	
	@DateTimeFormat
	private Calendar dataLancamento;
	
	private String sumarioPath;
	
	
	public BigDecimal precoPara(TipoPreco tipoPreco) {
	    return precos.stream().filter(preco -> preco.getTipoPreco().equals(tipoPreco)).findFirst().get().getValor();        
	}
		
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	@Override
	public String toString() {
		return "Trabalho [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}

	
	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public TipoModalidadeTrabalho getTipoModalidadeTrabalho() {
		return tipoModalidadeTrabalho;
	}

	public void setTipoModalidadeTrabalho(TipoModalidadeTrabalho tipoModalidadeTrabalho) {
		this.tipoModalidadeTrabalho = tipoModalidadeTrabalho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Trabalho other = (Trabalho) obj;
		if (id != other.id)
			return false;
		return true;
	}

					
}
