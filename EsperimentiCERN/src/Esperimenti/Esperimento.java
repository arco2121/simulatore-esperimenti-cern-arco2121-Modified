package Esperimenti;
import Exceptions.*;

import java.io.Serializable;
import java.util.Objects;

public abstract class Esperimento implements Serializable {
    protected String name;
    protected double energia;

    public Esperimento(String name, double energia) throws DatiEsperimentoNonValidiExeption {
        if(energia>0 || !Objects.equals(name, "")){
            this.name = name;
            this.energia = energia;
            return;
        }
        throw new DatiEsperimentoNonValidiExeption("Dati non validi");
    }

    public double energy() {
        return energia;
    }
    public String nome() {return name;}

    abstract public String descrizione();

    @Override
    public String toString() {
        return descrizione();
    }
}
