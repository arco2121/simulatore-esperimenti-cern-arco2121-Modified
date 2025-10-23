package Archivi;

import Esperimenti.Esperimento;

import java.io.*;
import java.util.*;

public class MappaScoperte implements Serializable {
    private Map<String,Esperimento> map;

    public MappaScoperte(Map.Entry<String, Esperimento>... scoperte) {
        map = new HashMap<>();
        for(Map.Entry<String, Esperimento> i : scoperte) {
            if(i != null)
            {
                this.map.put(i.getKey(),i.getValue());
            }
        }
    }

    public boolean aggiungiScoperta(String nome ,Esperimento esperimente)
    {
        try
        {
            this.map.put(nome,esperimente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean rimuoviEsperimento(String esperimente)
    {
        try
        {
            this.map.remove(esperimente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Set<String> elencoChiavi(int energia)
    {
        return this.map.keySet();
    }

    public Collection<Esperimento> elencoEsperimenti(int energia)
    {
        return this.map.values();
    }

    public boolean salvaSuFile(String nome)
    {
        //Uso il try catch per impedire che si blocchi l0esecuzione, cattuando eccezioni, e con questa sintassi evito di dover usare il finally
        try(ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(nome)))
        {
            file.writeObject(this);
            return true;
        }
        catch (Exception e)
        {
            System.err.println(new StringBuilder("Errore nel salvataggio della mappa: ").append(e.getMessage()));
            return false;
        }
    }

    public static MappaScoperte caricaDaFile(String nome)
    {
        //Uso il try catch per impedire che si blocchi l0esecuzione, cattuando eccezioni, e con questa sintassi evito di dover usare il finally
        try(ObjectInputStream file = new ObjectInputStream(new FileInputStream(nome)))
        {
            return (MappaScoperte) file.readObject();
        }
        catch (Exception e)
        {
            System.err.println(new StringBuilder("Errore nel caricare file: ").append(e.getMessage()));;
            return null;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("Mappa : \n");
        for(Esperimento k : map.values())
        {
            s.append(k.descrizione());
        }
        return s.toString();
    }
}
