package cad.afc.cad.api.controller;

import cad.afc.cad.api.aluno.Aluno;
import cad.afc.cad.api.aluno.AlunoRepository;
import cad.afc.cad.api.faltas.DadosCadastroFalta;
import cad.afc.cad.api.faltas.DadosListagemFalta;
import cad.afc.cad.api.faltas.DadosListagemFalta;
import cad.afc.cad.api.faltas.Falta;
import cad.afc.cad.api.faltas.FaltaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faltas")
public class FaltaController {

    @Autowired
    private FaltaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public void registrarFalta(@RequestBody @Valid DadosCadastroFalta dados) {

        Aluno aluno = alunoRepository.getReferenceById(dados.alunoId());


        aluno.adicionarFalta(dados.quantidade());


        Falta falta = new Falta(null, dados.data(), dados.quantidade(), aluno);
        repository.save(falta);
    }

    @GetMapping
    public java.util.List<DadosListagemFalta> listar() {
        return repository.findAll()
                .stream()
                .map(DadosListagemFalta::new)
                .toList();
    }
}