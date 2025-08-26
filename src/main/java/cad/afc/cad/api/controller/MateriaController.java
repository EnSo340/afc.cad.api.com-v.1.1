// Exemplo de MateriaController.java

package cad.afc.cad.api.controller;

import cad.afc.cad.api.materia.DadosCadastroMateria;
import cad.afc.cad.api.materia.Materia;
import cad.afc.cad.api.materia.MateriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaRepository repository;

    @PostMapping
    public void cadastrarMateria(@RequestBody @Valid DadosCadastroMateria dados) {
        repository.save(new Materia(dados));
    }

    @GetMapping
    public List<Materia> listarMateria() {
        return repository.findAll();
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Matéria não encontrada para deletar");
        }
        repository.deleteById(id);
    }

}