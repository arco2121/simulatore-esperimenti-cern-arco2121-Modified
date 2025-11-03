package Graphic;

import Esperimenti.*;
import Exceptions.*;
import javax.swing.*;
import java.awt.*;

public class DettagliDialog extends JDialog {

    private Esperimento esperimento;
    private JTextField txtNome;
    private JTextField txtEnergia;
    private JTextField txtParticella;

    public DettagliDialog(JFrame parent, Esperimento esperimento) {
        super(parent, "Dettagli Esperimento", true);
        this.esperimento = esperimento;

        setSize(400, 250);
        setLayout(new GridLayout(0, 2, 5, 5));

        add(new JLabel("Nome:"));
        txtNome = new JTextField(esperimento.nome());
        add(txtNome);

        add(new JLabel("Energia (TeV):"));
        txtEnergia = new JTextField(String.valueOf(esperimento.energy()));
        add(txtEnergia);

        // campo extra per EsperimentoRilevamento
        if (esperimento instanceof EsperimentoRilevamento) {
            add(new JLabel("Tipo Particella:"));
            String tipo = ((EsperimentoRilevamento) esperimento).descrizione().split("Tipo : ")[1].trim();
            txtParticella = new JTextField(tipo);
            add(txtParticella);
        }

        JButton salva = new JButton("Salva modifiche");
        salva.addActionListener(e -> salvaModifiche());
        add(new JLabel());
        add(salva);
    }

    private void salvaModifiche() {
        try {
            String nome = txtNome.getText();
            double energia = Double.parseDouble(txtEnergia.getText());

            if (esperimento instanceof EsperimentoRilevamento && txtParticella != null) {
                String tipo = txtParticella.getText();
                esperimento = new EsperimentoRilevamento(nome, energia, tipo);
            } else {
                JOptionPane.showMessageDialog(this, "Tipo non supportato per modifica.");
            }

            JOptionPane.showMessageDialog(this, "Modifiche salvate!");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore nel salvataggio: " + ex.getMessage());
        }
    }
}
