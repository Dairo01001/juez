package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Player;

import java.awt.event.ActionListener;

public class App  extends JFrame {

    private CardLayout views;
    private JPanel mainView;
    private FileChooser saveView;
    private Config configView;
    private Game playView;
    private Player player;
            
    public App() throws HeadlessException {
        init();
        initComponents();
    }
    
    private void init() {
        setTitle("Quizz");
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void initComponents() {
        player = new Player();

        views = new CardLayout();
        mainView = new JPanel(views);
        
        configView = new Config();
        configView.getExit().addActionListener(new ActionExit());
        configView.getAddQuestion().addActionListener(new ActionAddQuestion());
        configView.getPlay().addActionListener(new ActionPlay());
        mainView.add(configView, "config");


        playView = new Game();
        mainView.add(playView, "play");

        saveView = new FileChooser();
        mainView.add(saveView, "save");

        
        add(mainView, BorderLayout.CENTER);
    }

    public void showConfig() {
        views.show(mainView, "config");
    }

    public void showPlay() {
        views.show(mainView, "play");
    }

    public void showSave() {
        views.show(mainView, "save");
    }

    private class ActionExit implements ActionListener {
            
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.exit(0);
        }
    }

    private class ActionAddQuestion implements ActionListener {
            
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            String password =  JOptionPane.showInputDialog(mainView, "Ingresa la Contraseña");
            if (password.equals(utils.Utils.PASSWORD)) {
                showSave();
            } else {
                JOptionPane.showMessageDialog(mainView, "Contraseña incorrecta");
            }
        }
    }

    private class ActionPlay implements ActionListener {
            
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {

            String namePlayer = configView.getNamePlayer();

            if(!namePlayer.equals("")) {
                player.setName(namePlayer);
                setTitle(namePlayer);
                initGame();
                showPlay();
            } else {
                JOptionPane.showMessageDialog(mainView, "Ingresa un nombre");
            }
        }
    }

    private void initGame() {
        playView.setImg("/imgFiles/primer.png");
    }
    
    public static void main(String[] args) {
       App app = new App();
       app.pack();
       app.setVisible(true);
    }
}
