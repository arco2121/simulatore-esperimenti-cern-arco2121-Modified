package Graphic;
import Exceptions.*;
import Archivi.*;
import Esperimenti.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimulatoreEsperimenti extends JFrame {

    private ArchivioEsperimenti archivio;
    private MappaScoperte preferiti;

    private DefaultListModel<Esperimento> modelloArchivio;
    private DefaultListModel<String> modelloPreferiti;

    private JList<Esperimento> listaArchivio;
    private JList<String> listaPreferiti;

    public SimulatoreEsperimenti(ArchivioEsperimenti archivio, MappaScoperte preferiti) {
        this.archivio = archivio;
        this.preferiti = preferiti;

        setTitle("Gestione Esperimenti");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ======= LISTA ARCHIVIO =======
        modelloArchivio = new DefaultListModel<>();
        for (Esperimento e : archivio.getValues()) {  // mostro tutti
            modelloArchivio.addElement(e);
        }
        listaArchivio = new JList<>(modelloArchivio);
        JScrollPane scrollArchivio = new JScrollPane(listaArchivio);
        scrollArchivio.setBorder(BorderFactory.createTitledBorder("Archivio Esperimenti"));

        // ======= LISTA PREFERITI =======
        modelloPreferiti = new DefaultListModel<>();
        for (String k : preferiti.elencoChiavi()) {
            modelloPreferiti.addElement(k);
        }
        listaPreferiti = new JList<>(modelloPreferiti);
        JScrollPane scrollPreferiti = new JScrollPane(listaPreferiti);
        scrollPreferiti.setBorder(BorderFactory.createTitledBorder("Preferiti (Mappa Scoperte)"));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollArchivio, scrollPreferiti);
        add(split, BorderLayout.CENTER);

        // ======= PANNELLO BOTTONI =======
        JPanel panelButtons = new JPanel();
        JButton btnDettagli = new JButton("Mostra Dettagli");
        JButton btnAggiungiPref = new JButton("Aggiungi ai Preferiti");
        JButton btnRimuoviPref = new JButton("Rimuovi dai Preferiti");
        JButton btnSalva = new JButton("Salva Dati");
        JButton btnCarica = new JButton("Carica Dati");

        panelButtons.add(btnDettagli);
        panelButtons.add(btnAggiungiPref);
        panelButtons.add(btnRimuoviPref);
        panelButtons.add(btnSalva);
        panelButtons.add(btnCarica);
        add(panelButtons, BorderLayout.SOUTH);

        // ======= EVENTI =======
        btnDettagli.addActionListener(e -> mostraDettagli());
        btnAggiungiPref.addActionListener(e -> aggiungiPreferito());
        btnRimuoviPref.addActionListener(e -> rimuoviPreferito());
        btnSalva.addActionListener(e -> salvaTutto());
        btnCarica.addActionListener(e -> caricaTutto());

        // ======= DOPPIO CLICK =======
        listaArchivio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) mostraDettagli();
            }
        });
        listaPreferiti.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) mostraDettagliPreferito();
            }
        });
    }

    // ======= MOSTRA DETTAGLI DI ESPERIMENTO =======
    private void mostraDettagli() {
        Esperimento e = listaArchivio.getSelectedValue();
        if (e == null) return;

        new DettagliDialog(this, e).setVisible(true);
        listaArchivio.repaint();  // aggiorna la visuale
    }

    private void mostraDettagliPreferito() {
        String chiave = listaPreferiti.getSelectedValue();
        if (chiave == null) return;
        Esperimento e = preferiti.elencoEsperimenti().stream()
                .filter(exp -> exp.descrizione().contains(chiave))
                .findFirst().orElse(null);
        if (e != null)
            new DettagliDialog(this, e).setVisible(true);
    }

    private void aggiungiPreferito() {
        Esperimento selezionato = listaArchivio.getSelectedValue();
        if (selezionato == null) return;

        String nome = selezionato.toString().split(":")[1].trim();
        preferiti.aggiungiScoperta(nome, selezionato);
        modelloPreferiti.addElement(nome);
    }

    private void rimuoviPreferito() {
        String chiave = listaPreferiti.getSelectedValue();
        if (chiave != null) {
            preferiti.rimuoviEsperimento(chiave);
            modelloPreferiti.removeElement(chiave);
        }
    }

    private void salvaTutto() {
        archivio.salvaSuFile("archivio.dat");
        preferiti.salvaSuFile("preferiti.dat");
        JOptionPane.showMessageDialog(this, "Dati salvati correttamente.");
    }

    private void caricaTutto() {
        ArchivioEsperimenti a = ArchivioEsperimenti.caricaDaFile("archivio.dat");
        MappaScoperte p = MappaScoperte.caricaDaFile("preferiti.dat");
        if (a != null) this.archivio = a;
        if (p != null) this.preferiti = p;
        JOptionPane.showMessageDialog(this, "Dati caricati correttamente.");
    }
}