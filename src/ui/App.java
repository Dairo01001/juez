package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Player;
import utils.Utils;

import java.awt.event.ActionListener;

public class App  extends JFrame {

    private CardLayout views;
    private JPanel mainView;
    private FileChooser saveView;
    private PanelConfig panelConfig;
    private Player player;
    private PanelGame panelGame;
            
    public App() throws HeadlessException {
        init();
        initComponents();
    }
    
    private void init() {
        setTitle("Quizz");
        setResizable(false);
        setSize(Utils.WIDTH, Utils.HEIGHT);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void initComponents() {
        player = new Player();

        views = new CardLayout();
        mainView = new JPanel(views);
        
        panelConfig = new PanelConfig();
        panelConfig.getButtonExit().addActionListener(new ActionExit());
        panelConfig.getButtonAddQuestion().addActionListener(new ActionAddQuestion());
        panelConfig.getButtonPlay().addActionListener(new ActionPlay());
        mainView.add(panelConfig, "config");

        saveView = new FileChooser();
        mainView.add(saveView, "save");

        panelGame = new PanelGame();
        panelGame.getButtonNext().addActionListener(new ActionNextQuestion());
        panelGame.setImg("/data/Question"+ 1 +".png");
        mainView.add(panelGame, "panelGame");
        
        add(mainView, BorderLayout.CENTER);
    }

    public void showConfig() {
        views.show(mainView, "config");
    }

    public void showSave() {
        views.show(mainView, "save");
    }

    public void showPanelGame() {
        views.show(mainView, "panelGame");
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

            String namePlayer = panelConfig.getPlayerName();

            if(!namePlayer.equals("")) {
                player.setName(namePlayer);
                setTitle(namePlayer);
                showPanelGame();
            } else {
                JOptionPane.showMessageDialog(mainView, "Ingresa un nombre");
            }
        }
    }

    private class ActionNextQuestion implements ActionListener {

        private int questionNumber = 2;
        private int maxQuestions =  Utils.getNumberQuestion();

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if(questionNumber <= maxQuestions) {
                if(!panelGame.getAnswer().equals("")) {
                    if(panelGame.getAnswer().equals(Utils.getAnswer(new String[] {}, questionNumber))) {
                        player.setScore(player.getScore() + 1);
                    }            
                    
                    panelGame.setImg("/data/Question"+ questionNumber +".png");

                    questionNumber++;
                } else {
                    JOptionPane.showMessageDialog(mainView, "Ingresa una respuesta");
                }
                
            } else {
                JOptionPane.showMessageDialog(mainView, "No hay más preguntas");
            }
        }
    }
    
    public static void main(String[] args) {
       App app = new App();
       app.setVisible(true);
    }
}
