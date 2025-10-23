package Esperimenti;

import Exeptions.DatiEsperimentoNonValidiExeption;

import java.io.Serializable;

public class EsperimentoCollisione extends Esperimento implements Serializable {
    private final int numeroCollisioni;

    public EsperimentoCollisione(String name, double energia, int numeroCollisioni) throws DatiEsperimentoNonValidiExeption
    {
        super(name,energia);
        this.numeroCollisioni = numeroCollisioni;
    }

    public String descrizione()
    {
        return (new StringBuilder().append("\n\nNome Esperimento : ").append(name).append("\nEnergia : ").append(energia).append(" TeV").append("\nNumero Collisioni : ").append(numeroCollisioni).append("\n\n")).toString();
    }
}
