package cad.afc.cad.api.endereco;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class Endereco {
    private String logradouro;
    private String cep;
    private String bairro;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dados) {

        this.logradouro = dados.logradouro();
        this.cep = dados.cep();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.uf = dados.uf();
    }
}

