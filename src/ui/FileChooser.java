package ui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utils.Utils;

public class FileChooser extends JPanel {
    
    private JButton copyJavaButton;
    private JButton copyPNGButton;
    private JLabel pngLabel;
    private JLabel javaLabel;
    private JTextArea log;
    private JFileChooser fileChooser;
    private int questionNumber;
    private int questionCount;

    
    public FileChooser() {
        init();
        initComponents();
    }
    
    private void init() {
        setLayout(new BorderLayout());
    }
    
    private void initComponents() {
        fileChooser = new JFileChooser();
        
        copyJavaButton = new JButton("Cargar Java!");
        copyPNGButton = new JButton("Cargar PNG!");
        
        pngLabel = new JLabel("PNG");
        javaLabel = new JLabel("Java");

        log = new JTextArea(5, 20);
        log.setEditable(false);
        log.setMargin(new Insets(5, 5, 5, 5));

        ArrayList<String> listJava = Utils.listOfFileString(Utils.DIR_JAVA, ".java");
        ArrayList<String> listPNG = Utils.listOfFileString(Utils.DIR_IMG, ".png");

        questionNumber  = Math.min(listJava.size(), listPNG.size()) + 1;
        questionCount = 0;
        
        log.append("Java: " + listJava.toString() + "\n");
        log.append("PNG: " + listPNG.toString() + "\n");

        copyJavaButton.addActionListener(new ActionCopyJava());
        copyPNGButton.addActionListener(new ActionCopyPNG());
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(copyJavaButton);
        buttonsPanel.add(copyPNGButton);

        JPanel labelsPanel = new JPanel();
        labelsPanel.add(pngLabel);
        labelsPanel.add(javaLabel);

        JScrollPane logScrollPane = new JScrollPane(log);
        

        add(buttonsPanel, BorderLayout.NORTH);
        add(labelsPanel, BorderLayout.SOUTH);
        add(logScrollPane, BorderLayout.CENTER);
    }
    
    private class ActionCopyPNG implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            int val = fileChooser.showOpenDialog(FileChooser.this);
            
            if (val == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                File copyOfFile = new File(Utils.DIR_IMG + Utils.SEPARATOR + "Question" + questionNumber + ".png");

                Utils.copyFile(file, copyOfFile);
                if (copyOfFile.exists()) {
                    
                    if (questionCount == 1) {
                        log.append("Se copio el archivo " + file.getName() + " a " + copyOfFile.getName() + "\n");
                        questionCount = 0;
                        questionNumber++;
                    } else {
                        log.append("Se copio el archivo " + file.getName() + " a " + copyOfFile.getName() + " pero no se ha terminado de copiar todos los archivos\n");
                        questionCount++;
                    }
                    pngLabel.setText("PNG cargado!");
                }
            }
        }        
    }
    
    private class ActionCopyJava implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            int val = fileChooser.showOpenDialog(FileChooser.this);
            
            if (val == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                File copyOfFile = new File(Utils.DIR_JAVA + Utils.SEPARATOR + "Question" + questionNumber + ".java");
                
                Utils.copyFile(file, copyOfFile);
                if (copyOfFile.exists()) {
                    if (questionCount == 1) {
                        log.append("Se copio el archivo " + file.getName() + " a " + copyOfFile.getName() + "\n");
                        questionCount = 0;
                        questionNumber++;
                    } else {
                        log.append("Se copio el archivo " + file.getName() + " a " + copyOfFile.getName() + " pero no se ha terminado de copiar todos los archivos\n");
                        questionCount++;
                    }

                    javaLabel.setText("Java cargado!");
                }
            }
        }
    }
}
