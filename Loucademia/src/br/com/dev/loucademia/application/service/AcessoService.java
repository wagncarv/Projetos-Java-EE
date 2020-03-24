package br.com.dev.loucademia.application.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.dev.loucademia.application.util.StringUtils;
import br.com.dev.loucademia.application.util.ValidationException;
import br.com.dev.loucademia.domain.acesso.Acesso;
import br.com.dev.loucademia.domain.acesso.AcessoRepository;
import br.com.dev.loucademia.domain.acesso.TipoAcesso;
import br.com.dev.loucademia.domain.aluno.Aluno;
import br.com.dev.loucademia.domain.aluno.AlunoRepository;

@Stateless
public class AcessoService {
	
	@EJB
	private AcessoRepository acessoRepository;
	
	@EJB
	private AlunoRepository alunoRepository;
	
	public TipoAcesso registrarAcesso(String matricula, Integer rg) {
		if (StringUtils.isEmpty(matricula) && rg == null) {
			throw new ValidationException("Forneça a matrícula ou RG do aluno");
		}
		
		Aluno aluno;
		if (StringUtils.isEmpty(matricula)) {
			aluno = alunoRepository.findByRG(rg);
		}else {
			aluno = alunoRepository.findByMatricula(matricula);
		}
		
		if (aluno == null) {
			throw new ValidationException("Aluno não encontrado");
		}
		
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
		TipoAcesso tipoAcesso;
		
		if (ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
			ultimoAcesso = new Acesso();
			ultimoAcesso.setAluno(aluno);
			tipoAcesso = ultimoAcesso.registrarAcesso();
			acessoRepository.store(ultimoAcesso);
		}else {
			tipoAcesso = ultimoAcesso.registrarAcesso();
		}
		return tipoAcesso;
	}

}
