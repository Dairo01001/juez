package ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
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
        
        name = new JTextField(10);

        add(play, BorderLayout.NORTH);
        add(exit, BorderLayout.SOUTH);
        add(addQuestion, BorderLayout.CENTER);
        add(name, BorderLayout.EAST);
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

    private JButton play;
    private JButton exit;
    private JButton addQuestion;
    private JTextField name;
}
