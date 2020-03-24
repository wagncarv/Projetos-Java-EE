package br.com.dev.loucademia.interfaces.shared.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.dev.loucademia.domain.aluno.Aluno.Sexo;
import br.com.dev.loucademia.domain.aluno.Aluno.Situacao;
import br.com.dev.loucademia.application.service.DataService;
import br.com.dev.loucademia.domain.aluno.Estado;

@Named
@ApplicationScoped
public class DataBean implements Serializable {
	
	@EJB
	private DataService dataService;

	public Sexo[] getSexos() {
		return dataService.getSexos();
	}
	
	public Situacao[] getSituacoes() {
		return dataService.getSituacoes();
	}
	
	public List<Estado> getEstados(){
		return dataService.listEstados();
	}
	
	public String formatTelefone(Integer ddd, Integer numero) {
		String s = "";
		if (ddd == null || numero == null) {
			return "";
		}
		if (numero.toString().length() == 9) {
			s = numero.toString().substring(0, 1) + " " + numero.toString().substring(1, 5) + "-" + numero.toString().substring(5);
		}else {
			s =  numero.toString().substring(0, 4) + "-" + numero.toString().substring(4);
		}
		return "(" + ddd + ") " + s;
	}
}
