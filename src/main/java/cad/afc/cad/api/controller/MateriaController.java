// Exemplo de MateriaController.java

package cad.afc.cad.api.controller;

import cad.afc.cad.api.materia.DadosCadastroMateria;
import cad.afc.cad.api.materia.Materia;
import cad.afc.cad.api.materia.MateriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaRepository repository;

    @PostMapping
    public void cadastrarMateria(@RequestBody @Valid DadosCadastroMateria dados) {
        repository.save(new Materia(dados));
    }
}