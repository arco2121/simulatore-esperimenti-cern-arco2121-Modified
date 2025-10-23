package Esperimenti;

import Exeptions.DatiEsperimentoNonValidiExeption;

import java.io.Serial;
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
        return (new StringBuilder().append("\n\nNome Esperimento : ").append(name).append("\nEnergia : ").append(energia).append(" TeV").append("\nTipo : ").append(tipoParticella).append("\n\n")).toString();
    }
}
