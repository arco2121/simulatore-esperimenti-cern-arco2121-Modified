package Main;

import Archivi.ArchivioEsperimenti;
import Archivi.MappaScoperte;
import Esperimenti.*;
import Exceptions.DatiEsperimentoNonValidiExeption;
import Graphic.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static ArchivioEsperimenti mainLine() {
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
            archivio.aggiungiEsperimento(n3);
            archivio.aggiungiEsperimento(n4);
            mappaScoperte.aggiungiScoperta("Massa alle particelle",n3);
            mappaScoperte.aggiungiScoperta("Neutrini in Lab",n4);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        //Per entrambe le raccolte salvo sul file e se l'opazione riesce li ricarica e li stampa, visto che il metodo di salvataggio
        //restituisce se va a buon fine o no
        boolean res = mappaScoperte.salvaSuFile("Mappa.cern");
        if(res)
        {
            MappaScoperte fine = MappaScoperte.caricaDaFile("Mappa.cern");
            System.out.println(fine);
        }
        boolean res2 = archivio.salvaSuFile("Lista.cern");
        if(res2)
        {
            ArchivioEsperimenti fine = ArchivioEsperimenti.caricaDaFile("Lista.cern");
            System.out.println(fine);
            return fine;
        }
        return archivio;
    }
    public static void mainGraphic() throws DatiEsperimentoNonValidiExeption {
        ArchivioEsperimenti archivio = new ArchivioEsperimenti();
        Esperimento n1 = new EsperimentoRilevamento("Effetto fotoelettrico",23.12, "Fotone");
        Esperimento n2 = new EsperimentoCollisione("Propagazione dell'onda",230.23,3221);
        archivio.aggiungiEsperimento(n1);
        archivio.aggiungiEsperimento(n2);
        Esperimento n3 = new EsperimentoSimulazione("Bosone Higgs",4000.3,2015);
        Esperimento n4 = new EsperimentoCollisione("Neutrini",7011.3,2019);
        archivio.aggiungiEsperimento(n3);
        archivio.aggiungiEsperimento(n4);
        System.out.print(archivio);
        MappaScoperte preferiti = new MappaScoperte();
        SwingUtilities.invokeLater(() ->
                new SimulatoreEsperimentiForm(archivio, preferiti).setVisible(true)
        );
    }
    public static void main(String[] args) throws DatiEsperimentoNonValidiExeption
    {
        mainGraphic();
    }
}