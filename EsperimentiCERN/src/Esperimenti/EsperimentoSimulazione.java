package Esperimenti;

import Exceptions.DatiEsperimentoNonValidiExeption;

import java.io.Serializable;

public class EsperimentoSimulazione extends Esperimento implements Serializable {
    private final int annoSimulazione;

    public EsperimentoSimulazione(String name, double energia, int annoSimulazione) throws DatiEsperimentoNonValidiExeption
    {
        super(name,energia);
        this.annoSimulazione = annoSimulazione;
    }

    public String descrizione()
    {
        return (new StringBuilder().append("\n\nNome Esperimento : ").append(name).append("\t  Anno Simulazione : ").append(annoSimulazione).append("\t  Energia : ").append(energia).append(" TeV").append("\n\n")).toString();
    }
}
