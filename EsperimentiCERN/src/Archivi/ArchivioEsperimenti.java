package Archivi;
import Esperimenti.Esperimento;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArchivioEsperimenti implements Serializable {
    private List<Esperimento> esperimenti;

    public ArchivioEsperimenti(Esperimento... esperimenti) {
        this.esperimenti = new ArrayList<Esperimento>();
        for(Esperimento i : esperimenti) {
            if(i != null)
            {
                this.esperimenti.add(i);
            }
        }
    }

    public boolean aggiungiEsperimento(Esperimento esperimente)
    {
        try
        {
            this.esperimenti.add(esperimente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean rimuoviEsperimento(Esperimento esperimente)
    {
        try
        {
            this.esperimenti.remove(esperimente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int size()
    {
        return this.esperimenti.size();
    }

    public Iterator<Esperimento> iterator()
    {
        return this.esperimenti.iterator();
    }

    public List<Esperimento> ricercaEsperimentoDaEnergia(int energia)
    {
        final double max = energia + (double)(energia/10);
        final double min = energia - (double)(energia/10);
        List<Esperimento> esperimentiP = new ArrayList<Esperimento>();
        for (Esperimento i : this.esperimenti) {
            if(i.energy() < max && i.energy() > min)
            {
                esperimentiP.add(i);
            }
        }
        return esperimentiP;
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
            System.err.println(new StringBuilder("Errore nel salvataggio di esperimenti: ").append(e.getMessage()));
            return false;
        }
    }

    public static ArchivioEsperimenti caricaDaFile(String nome)
    {
        //Uso il try catch per impedire che si blocchi l0esecuzione, cattuando eccezioni, e con questa sintassi evito di dover usare il finally
        try(ObjectInputStream file = new ObjectInputStream(new FileInputStream(nome)))
        {
            return (ArchivioEsperimenti) file.readObject();
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
        s.append("Archivio : \n");
        for(Esperimento k : esperimenti)
        {
            s.append(k.descrizione());
        }
        return s.toString();
    }
}
