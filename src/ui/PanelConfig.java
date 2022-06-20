package ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelConfig extends JPanel {
    
    public PanelConfig() {
        init();
        initComponents();
    }

    private void init() {
        setLayout(new BorderLayout());        
    }

    private void initComponents() {
        play = new JButton("Jugar");
        exit = new JButton("Salir");
        addQuestion = new JButton("Agregar pregunta");
        viewScore = new JButton("Ver Score");
        
        name = new JTextField(10);
        
        
        JPanel panelAddQuestion = new JPanel();
        panelAddQuestion.add(addQuestion);
        panelAddQuestion.add(viewScore);

        
        JPanel panelOpciones = new JPanel();
        panelOpciones.add(play);
        panelOpciones.add(exit);
    
        JPanel panelConfig = new JPanel();
        panelConfig.add(new JLabel("Nombre: "));
        panelConfig.add(name);
        
        add(panelAddQuestion, BorderLayout.NORTH);
        add(panelOpciones, BorderLayout.SOUTH);
        add(panelConfig, BorderLayout.CENTER);
    }

    public JButton getButtonPlay() {
        return play;
    }

    public JButton getButtonExit() {
        return exit;
    }

    public JButton getButtonAddQuestion() {
        return addQuestion;
    }

    public String getPlayerName () {
        return name.getText();
    }
    
    public JButton getButtonScore() {
        return viewScore;
    }

    private JButton play;
    private JButton exit;
    private JButton addQuestion;
    private JButton viewScore;
    private JTextField name;
}
