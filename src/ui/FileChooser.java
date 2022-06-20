package ui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
        
        log.append("Java: " + String.join(", ", Utils.listOfFileString(Utils.DIR_JAVA)) + "\n");
        log.append("PNG: " + String.join(", ", Utils.listOfFileString(Utils.DIR_IMG)) + "\n");
        
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

                File copyOfFile = new File(Utils.DIR_IMG + Utils.SEPARATOR + file.getName());

                Utils.copyFile(file, copyOfFile);
                if (copyOfFile.exists()) {
                    log.append("Nuevo PNG copiado: " + copyOfFile.getName() + "\n");
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

                File copyOfFile = new File(Utils.DIR_JAVA + Utils.SEPARATOR + file.getName());
                
                Utils.copyFile(file, copyOfFile);
                if (copyOfFile.exists()) {
                    log.append("Nuevo Java copiado: " + copyOfFile.getName() + "\n");
                    javaLabel.setText("Java cargado!");
                }
            }
        }
    }
}
