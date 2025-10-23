package Exeptions;

public class DatiEsperimentoNonValidiExeption extends Exception{
    public DatiEsperimentoNonValidiExeption() {
        super();
    }
    public DatiEsperimentoNonValidiExeption(String message) {
        super(message);
    }
}
