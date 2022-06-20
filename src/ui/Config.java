package ui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Config extends JPanel {

    public Config() {
        initComponents();
    }

    private void initComponents() {

        jButtonAddQuestion = new javax.swing.JButton();
        jButtonPlay = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jLabelTitle = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();

        jButtonAddQuestion.setText("Agregar");

        jButtonPlay.setText("Jugar");

        jButtonExit.setText("Salir");

        jLabelTitle.setFont(new java.awt.Font("Fira Code Medium", 0, 36)); // NOI18N
        jLabelTitle.setText("Quizz");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jButtonPlay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jButtonExit)
                .addGap(85, 85, 85))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonAddQuestion)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelTitle)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAddQuestion)
                .addGap(24, 24, 24)
                .addComponent(jLabelTitle)
                .addGap(39, 39, 39)
                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlay)
                    .addComponent(jButtonExit))
                .addGap(15, 15, 15))
        );
    }

    public javax.swing.JButton getAddQuestion() {
        return jButtonAddQuestion;
    }

    public javax.swing.JButton getPlay() {
        return jButtonPlay;
    }

    public JButton getExit() {
        return jButtonExit;
    }
    
    public String getNamePlayer() {
        return jTextFieldName.getText();
    }

    private javax.swing.JButton jButtonAddQuestion;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JTextField jTextFieldName;
}
