package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository repository;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataDeNascimento(form.getDataDeNascimento());

        return repository.save(aluno);
    }

    @Override
    public Aluno get(Long id) {
        Aluno aluno = repository.findById(id).get();
        return aluno;
    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento, String bairro) {

        if (dataDeNascimento == null && bairro == null) {
            // Se nenhum parâmetro for passado, retorna todos os alunos.
            return repository.findAll();
        } else if (dataDeNascimento != null) {
            LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            if (bairro != null) {
                // Se for passada a dataDeNascimento e bairro como parâmetros, retorna todos os alunos que
                // condizem com esses parâmetros.
                return repository.findByDataDeNascimentoAndBairro(localDate, bairro);
            } else {
                // Se for passado apenas dataDeNascimento, buscar por esse parâmetro.
                return repository.findByDataDeNascimento(localDate);
            }
        } else {
            // Se for passado apenas o bairro, buscar por esse parâmetro.
            return repository.findByBairro(bairro);
        }
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) {
        Aluno aluno = repository.getById(id);
        if (formUpdate.getNome() != null){
            aluno.setNome(formUpdate.getNome());
        }
        if (formUpdate.getDataDeNascimento() != null){
            aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());
        }
        if (formUpdate.getBairro() != null) {
            aluno.setBairro(formUpdate.getBairro());
        }
        return repository.save(aluno);
    }

    @Override
    public void delete(Long id) {
        Aluno aluno = repository.findById(id).get();
        repository.delete(aluno);
    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {
        Aluno aluno = repository.findById(id).get();

        return aluno.getAvaliacoes();
    }

}
