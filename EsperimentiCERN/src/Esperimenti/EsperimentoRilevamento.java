package Esperimenti;

import Exceptions.DatiEsperimentoNonValidiExeption;

import java.io.Serializable;

public class EsperimentoRilevamento extends Esperimento implements Serializable {
    private final String tipoParticella;

    public EsperimentoRilevamento(String name, double energia, String tipoParticella) throws DatiEsperimentoNonValidiExeption
    {
        super(name,energia);
        this.tipoParticella = tipoParticella;
    }

    public String descrizione()
    {
        return (new StringBuilder().append("\n\nNome Esperimento : ").append(name).append("\t  Energia : ").append(energia).append(" TeV").append("\t  Tipo : ").append(tipoParticella).append("\n\n")).toString();
    }
}
