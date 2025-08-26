package cad.afc.cad.api.controller;

import cad.afc.cad.api.aluno.*;
import cad.afc.cad.api.aluno.DadosAtualizacaoAluno;
import cad.afc.cad.api.aluno.DadosCadastroAluno;
import cad.afc.cad.api.aluno.DadosListagemAluno;
import cad.afc.cad.api.faltas.TotalFaltasDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @Transactional
    public void cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dadosAluno) {
        Aluno aluno = new Aluno(dadosAluno);
        repository.save(aluno);
    }

    @GetMapping
    public List<DadosListagemAluno> listarAluno() {
        return repository.findAll()
                .stream()
                .map(DadosListagemAluno::new)
                .toList();
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizarAluno(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.getReferenceById(id);
        aluno.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirAluno(@PathVariable Long id) {
        repository.deleteById(id);
    }


    @GetMapping("/{id}/faltas")
    public TotalFaltasDTO getTotalFaltas(@PathVariable Long id) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID: " + id));

        return new TotalFaltasDTO(aluno.getTotalFaltas());
    }

    @GetMapping("/{id}/aprovacao")
    public SituacaoAlunoDTO verificarAprovacao(@PathVariable Long id) {
        return alunoService.verificarAprovacao(id);
    }


}
