package Esperimenti;

import Exeptions.DatiEsperimentoNonValidiExeption;

public class EsperimentoSimulazione extends Esperimento{
    private final int annoSimulazione;

    public EsperimentoSimulazione(String name, double energia, int annoSimulazione) throws DatiEsperimentoNonValidiExeption
    {
        super(name,energia);
        this.annoSimulazione = annoSimulazione;
    }

    public String descrizione()
    {
        return (new StringBuilder().append("\n\nNome Esperimento : ").append(name).append("\nAnno Simulazione : ").append(annoSimulazione).append("\nEnergia : ").append(energia).append(" TeV").append("\n\n")).toString();
    }
}
