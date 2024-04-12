package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @PostMapping
    public Aluno create(@Valid  @RequestBody AlunoForm form) {
        return service.create(form);
    }

    @GetMapping("avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
        return service.getAllAvaliacaoFisicaId(id);
    }

    @GetMapping
    public List<Aluno> getAlunos(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento,
                                 @RequestParam(value = "bairro", required = false) String bairro) {

        if (id == null) {
            return getAll(dataDeNascimento, bairro);
        }
        return List.of(get(id));
    }

    public List<Aluno> getAll(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento,
                              @RequestParam(value = "bairro", required = false) String bairro) {
        return service.getAll(dataDeNascimento, bairro);
    }

    public Aluno get(@RequestParam(value = "id") Long id) {
        return service.get(id);
    }

    @PutMapping
    public Aluno update(@RequestParam Long id,@Valid @RequestBody AlunoUpdateForm form) {
        return service.update(id, form);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        service.delete(id);
    }

}
