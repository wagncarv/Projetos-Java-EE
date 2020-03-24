package br.com.dev.loucademia.application.service;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.dev.loucademia.application.util.StringUtils;
import br.com.dev.loucademia.application.util.Validation;
import br.com.dev.loucademia.application.util.ValidationException;
import br.com.dev.loucademia.domain.acesso.Acesso;
import br.com.dev.loucademia.domain.aluno.Aluno;
import br.com.dev.loucademia.domain.aluno.AlunoRepository;
import br.com.dev.loucademia.domain.aluno.Aluno.Situacao;

@Stateless
public class AlunoService {
	
	@EJB
	private AlunoRepository alunoRepository;
	
	public void createOrUpdate(Aluno aluno) {
		if (StringUtils.isEmpty(aluno.getMatricula())) {
			create(aluno);
		}else {
			update(aluno);
		}
		
	}
	
	private void create(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		
		String maxMatricula = alunoRepository.getMaxMatriculaAno();
		aluno.gerarMatricula(maxMatricula);
		alunoRepository.store(aluno);
	}
	
	private void update(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		Validation.assertNotEmpty(aluno.getMatricula());
		
		alunoRepository.update(aluno);
	}
	
	public void delete(String matricula) {
		alunoRepository.delete(matricula);
	}
	
	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}
	
	public List<Aluno> listAlunos(String matricula, String nome, Integer rg, Integer telefone){
		if(StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && rg == null && telefone == null) {
			throw new ValidationException("Pelo menos um critério de pesquisa deve ser fornecido!");
		}
		return alunoRepository.listAlunos(matricula, nome, rg, telefone);
	}
	
	public List<Aluno> listSituacoesAlunos(Situacao situacao){
		Validation.assertNotEmpty(situacao);
		return alunoRepository.listSituacoesAlunos(situacao);
	}
	
	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal){
		if (StringUtils.isEmpty(matricula) && dataInicial == null && dataFinal == null) {
			throw new ValidationException("Pelo menos um critério de pesquisa deve ser fornecido!"); 
		}
		return alunoRepository.listAcessosAlunos(matricula, dataInicial, dataFinal);
	}
	
}
