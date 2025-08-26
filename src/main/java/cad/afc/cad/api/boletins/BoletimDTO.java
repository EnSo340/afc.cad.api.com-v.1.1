package cad.afc.cad.api.boletins;

import cad.afc.cad.api.boletins.Boletim;
import cad.afc.cad.api.boletins.TipoDeEnsino; // Importação correta

public record BoletimDTO(
        Long boletimId,
        String materia,
        TipoDeEnsino.TipoAvaliacao tipoAvaliacao, // <--- CORRIGIDO
        Integer periodo,
        Double p1,
        Double p2,
        Double atv,
        Double media
) {
    public BoletimDTO(Boletim boletim) {
        this(
                boletim.getId(),
                boletim.getMateria().getNome(),
                boletim.getTipoAvaliacao(),
                boletim.getPeriodo(),
                boletim.getP1(),
                boletim.getP2(),
                boletim.getAtv(),
                boletim.getMedia()
        );
    }
}