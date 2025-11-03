package Esperimenti;

import Exceptions.DatiEsperimentoNonValidiExeption;

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
        return (new StringBuilder().append("\n\nNome Esperimento : ").append(name).append("\t  Energia : ").append(energia).append(" TeV").append("\t  Numero Collisioni : ").append(numeroCollisioni).append("\n\n")).toString();
    }
}
