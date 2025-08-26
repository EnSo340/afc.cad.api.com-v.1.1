package cad.afc.cad.api.controller;

import cad.afc.cad.api.boletins.BoletimDTO;
import cad.afc.cad.api.boletins.BoletimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/boletins")
public class BoletimController {

    @Autowired
    private BoletimService boletimService;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public BoletimDTO cadastrar(@RequestBody @Valid BoletimDTO dados) {
        return boletimService.salvar(dados);
    }

    @GetMapping("/{id}")
    public BoletimDTO detalhar(@PathVariable Long id) {
        return boletimService.buscarPorId(id);
    }

    @GetMapping
    public Page<BoletimDTO> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return boletimService.listar(paginacao);
    }

    @PutMapping("/{id}")
    @Transactional
    public BoletimDTO atualizar(@PathVariable Long id, @RequestBody @Valid BoletimDTO dados) {
        return boletimService.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        boletimService.excluir(id);
    }
}