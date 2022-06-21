package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Player;
import java.awt.event.ActionEvent;
import utils.Utils;

import java.awt.event.ActionListener;
import java.util.Random;

public class App extends JFrame {

    private CardLayout views;
    private JPanel mainView;
    private FileChooser saveView;
    private PanelConfig panelConfig;
    private PanelScore panelScore;
    private Player player;
    private PanelGame panelGame;
    private int questionNumber;
    private Random random;
    private String[] args;

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
        random = new Random();
        args = new String[2];
        questionNumber = 1;
        argsGenerate();

        views = new CardLayout();
        mainView = new JPanel(views);

        panelConfig = new PanelConfig();
        panelConfig.getButtonExit().addActionListener(new ActionExit());
        panelConfig.getButtonAddQuestion().addActionListener(new ActionAddQuestion());
        panelConfig.getButtonPlay().addActionListener(new ActionPlay());
        panelConfig.getButtonScore().addActionListener(new ActionShowScore());
        mainView.add(panelConfig, "config");

        saveView = new FileChooser();
        mainView.add(saveView, "save");

        panelGame = new PanelGame();
        panelGame.getButtonNext().addActionListener(new ActionNextQuestion());
        panelGame.setArgs(args);
        panelGame.setImg("/data/Question" + questionNumber + ".png");
        mainView.add(panelGame, "panelGame");

        panelScore = new PanelScore();
        panelScore.getBack().addActionListener(new ActionBack());
        panelScore.getLogScore().append(Utils.getPuntaje());
        mainView.add(panelScore, "score");

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

    public void showScore() {
        views.show(mainView, "score");
    }

    private void argsGenerate() {
        args[0] = String.valueOf(random.nextInt(10));
        args[1] = String.valueOf(random.nextInt(10));
    }
    
    private class  ActionBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            showConfig();
        }
    }

    private class ActionShowScore implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String password = JOptionPane.showInputDialog(mainView, "Ingresa la Contraseña");
            if (password != null) {
                if (password.equals(Utils.PASSWORD)) {
                    showScore();
                } else {
                    JOptionPane.showMessageDialog(mainView, "Contraseña incorrecta");
                }
            }
        }
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
            String password = JOptionPane.showInputDialog(mainView, "Ingresa la Contraseña");
            if (password != null) {
                if (password.equals(utils.Utils.PASSWORD)) {
                    showSave();
                } else {
                    JOptionPane.showMessageDialog(mainView, "Contraseña incorrecta");
                }
            }
        }
    }

    private class ActionPlay implements ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {

            String namePlayer = panelConfig.getPlayerName();

            if (!namePlayer.equals("")) {
                player.setName(namePlayer);
                setTitle(namePlayer);
                showPanelGame();
            } else {
                JOptionPane.showMessageDialog(mainView, "Ingresa un nombre");
            }
        }
    }

    private class ActionNextQuestion implements ActionListener {

        private int maxQuestions = Utils.getNumberQuestion();

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if (questionNumber <= maxQuestions) {
                if (!panelGame.getAnswer().equals("")) {

                    String answer = Utils.getAnswer(args, questionNumber);

                    if (panelGame.getAnswer().equals(answer)) {
                        player.setScore(player.getScore() + 1);
                    } else {
                        JOptionPane.showMessageDialog(mainView, "Respuesta incorrecta");
                    }

                    panelGame.clearAnswer();
                    
                    if (questionNumber == maxQuestions) {
                        JOptionPane.showMessageDialog(mainView, "Juego terminado");
                        String line = String.format("%10s %5s\n", player.getName(), player.getScore());
                        Utils.setPuntaje(line);
                        panelScore.getLogScore().append(line);
                        showConfig();
                    } else {
                        questionNumber++;
                        argsGenerate();
                        panelGame.setArgs(args);
                        panelGame.setImg("/data/Question" + questionNumber + ".png");
                    }
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
