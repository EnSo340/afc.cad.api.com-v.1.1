package cad.afc.cad.api.controller;

import cad.afc.cad.api.aluno.Aluno;
import cad.afc.cad.api.aluno.AlunoRepository;
import cad.afc.cad.api.faltas.DadosCadastroFalta;
import cad.afc.cad.api.faltas.DadosListagemFaltas;
import cad.afc.cad.api.faltas.Faltas;
import cad.afc.cad.api.faltas.FaltasRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faltas")
public class FaltasController {

    @Autowired
    private FaltasRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public void registrarFalta(@RequestBody @Valid DadosCadastroFalta dados) {

        Aluno aluno = alunoRepository.getReferenceById(dados.alunoId());


        aluno.adicionarFalta(dados.quantidade());


        Faltas falta = new Faltas(null, dados.data(), dados.quantidade(), aluno);
        repository.save(falta);
    }

    @GetMapping
    public java.util.List<DadosListagemFaltas> listar() {
        return repository.findAll()
                .stream()
                .map(DadosListagemFaltas::new)
                .toList();
    }
}