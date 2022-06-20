package ui;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Utils;

import java.awt.BorderLayout;

public class PanelGame extends JPanel {
    
    public PanelGame() {
        init();
        initComponents();
    }

    private void init() {
        setLayout(new BorderLayout());
    }

    private void initComponents() {
        jLabelImg = new JLabel();

        JPanel panelControllers = new JPanel();
        jLabelAnswer = new JLabel("Respuesta: ");
        jTextFieldAnswer = new JTextField(10);
        jButtonNext = new JButton("Siguiente");

        panelControllers.add(jLabelAnswer);
        panelControllers.add(jTextFieldAnswer);
        panelControllers.add(jButtonNext);
    
        JPanel panelArgs = new JPanel();
        jLabelArgs = new JLabel("args[0] = \"\"");
        jLabelArgs1 = new JLabel("args[1] = \"\"");

        panelArgs.add(jLabelArgs);
        panelArgs.add(jLabelArgs1);


        
        add(jLabelImg, BorderLayout.CENTER);
        add(panelControllers, BorderLayout.SOUTH);
        add(panelArgs, BorderLayout.NORTH);
    }

    public JButton getButtonNext() {
        return jButtonNext;
    }

    public void setImg(String img) {
        Image image  = new javax.swing.ImageIcon(getClass().getResource(img)).getImage();
        jLabelImg.setIcon(new ImageIcon(image.getScaledInstance(Utils.WIDTH, Utils.HEIGHT - 150, Image.SCALE_SMOOTH)));
    }

    public void setArgs(String args) {
        jLabelArgs.setText("args[0] = " + args);
    }

    public void setArgs1(String args) {
        jLabelArgs1.setText("args[1] = " + args);
    }

    public void setArgs(String[] args) {
        jLabelArgs.setText("args[0] = " + args[0]);
        jLabelArgs1.setText("args[1] = " + args[1]);
    }

    public String getAnswer() {
        return jTextFieldAnswer.getText();
    }

    private JLabel jLabelAnswer;
    private JLabel jLabelArgs;
    private JLabel jLabelArgs1;
    private JTextField jTextFieldAnswer;
    private JLabel jLabelImg;
    private JButton jButtonNext;
}
