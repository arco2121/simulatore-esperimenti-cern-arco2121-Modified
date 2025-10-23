package Main;

import Archivi.ArchivioEsperimenti;
import Archivi.MappaScoperte;
import Esperimenti.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //Creo le raccolte
        ArchivioEsperimenti archivio = new ArchivioEsperimenti();
        MappaScoperte mappaScoperte = new MappaScoperte();
        try
        {
            //Aggiungo elementi gestendo le possibili eccezioni di DatiEsperimentiNonValidi
            Esperimento n1 = new EsperimentoRilevamento("Effetto fotoelettrico",23.12, "Fotone");
            Esperimento n2 = new EsperimentoCollisione("Propagazione dell'onda",230.23,3221);
            archivio.aggiungiEsperimento(n1);
            archivio.aggiungiEsperimento(n2);
            Esperimento n3 = new EsperimentoSimulazione("Bosone Higgs",4000.3,2015);
            Esperimento n4 = new EsperimentoCollisione("Neutrini",7011.3,2019);
            mappaScoperte.aggiungiScoperta("Massa alle particelle",n3);
            mappaScoperte.aggiungiScoperta("Neutrini in Lab",n4);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        //Per entrambe le raccolte salvo sul file e se l'opazione riesce li ricarica e li stampa, visto che il metodo di salvataggio
        //restituisce se va a buon fine o no
        boolean res = mappaScoperte.salvaSuFile("Mappa.cern");
        if(res == true)
        {
            MappaScoperte fine = MappaScoperte.caricaDaFile("Mappa.cern");
            System.out.println(fine);
            System.out.println(fine.toString());
        }
        boolean res2 = mappaScoperte.salvaSuFile("Lista.cern");
        if(res2 == true)
        {
            ArchivioEsperimenti fine = ArchivioEsperimenti.caricaDaFile("Lista.cern");
            System.out.println(fine.toString());
        }
    }
}
