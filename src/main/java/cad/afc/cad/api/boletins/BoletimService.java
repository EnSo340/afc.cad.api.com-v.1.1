package cad.afc.cad.api.boletins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BoletimService {

    @Autowired
    private BoletimRepository boletimRepository;


    public BoletimDTO salvar(BoletimDTO dados) {
        Boletim boletim = new Boletim(dados);
        boletimRepository.save(boletim);
        return new BoletimDTO(boletim);
    }


    public BoletimDTO buscarPorId(Long id) {
        Boletim boletim = boletimRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Boletim não encontrado com o ID: " + id));
        return new BoletimDTO(boletim);
    }


    public Page<BoletimDTO> listar(Pageable paginacao) {
        return boletimRepository.findAll(paginacao).map(BoletimDTO::new);
    }


    public BoletimDTO atualizar(Long id, BoletimDTO dados) {
        Boletim boletim = boletimRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Boletim não encontrado com o ID: " + id));



        boletimRepository.save(boletim);
        return new BoletimDTO(boletim);
    }


    public void excluir(Long id) {
        if (!boletimRepository.existsById(id)) {
            throw new EntityNotFoundException("Boletim não encontrado com o ID: " + id);
        }
        boletimRepository.deleteById(id);
    }
}