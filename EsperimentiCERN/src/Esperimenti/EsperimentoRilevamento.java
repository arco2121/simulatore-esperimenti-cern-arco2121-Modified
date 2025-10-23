package Esperimenti;

import Exeptions.DatiEsperimentoNonValidiExeption;

public class EsperimentoRilevamento extends Esperimento {
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
