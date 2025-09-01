package cad.afc.cad.api.controller;

import cad.afc.cad.api.aluno.Aluno;
import cad.afc.cad.api.aluno.AlunoRepository;
import cad.afc.cad.api.notas.Nota;
import cad.afc.cad.api.notas.NotaDetalhesDTO;
import cad.afc.cad.api.notas.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlunoRepository alunoRepository;




    @PostMapping("/{alunoId}")
    @Transactional
    public Nota criar(@PathVariable Long alunoId, @RequestBody Nota novaNota) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID: " + alunoId));

        // Verifica se o aluno já possui uma nota
        List<Nota> notasDoAluno = notaRepository.findByAlunoId(alunoId);
        if (!notasDoAluno.isEmpty()) {
            throw new IllegalArgumentException("O aluno já possui uma nota. Para adicionar uma nova, a anterior deve ser excluída.");
        }

        Nota nota = new Nota(novaNota.getP1(), novaNota.getP2(), novaNota.getAtv(), aluno);
        return notaRepository.save(nota);
    }

    @PutMapping("/{id}")
    @Transactional
    public Nota atualizar(@PathVariable Long id, @RequestBody Nota novaNota) {
        return notaRepository.findById(id)
                .map(nota -> {
                    nota.setP1(novaNota.getP1());
                    nota.setP2(novaNota.getP2());
                    nota.setAtv(novaNota.getAtv());
                    return notaRepository.save(nota);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String deletar(@PathVariable Long id) {
        return notaRepository.findById(id)
                .map(nota -> {
                    notaRepository.delete(nota);
                    return "Nota deletada com sucesso!";
                })
                .orElse("Nota não encontrada!");
    }
    @GetMapping
    public List<NotaDetalhesDTO> listar() {
        return notaRepository.findAll().stream()
                .map(NotaDetalhesDTO::new)
                .collect(Collectors.toList());
    }

    }