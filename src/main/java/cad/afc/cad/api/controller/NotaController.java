package cad.afc.cad.api.controller;

import cad.afc.cad.api.aluno.Aluno;
import cad.afc.cad.api.aluno.AlunoRepository;
import cad.afc.cad.api.notas.Nota;
import cad.afc.cad.api.notas.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                    nota.calcularMedia();
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
    public List<Nota> listar() {
        return notaRepository.findAll();
    }
}