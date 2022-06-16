package noghtekhat_server;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusForm extends javax.swing.JFrame {

    JLabel[] labelPlayers;
    JCheckBox[] boxPlayers;
    public StatusForm(int count, String IP, int port) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        this.setSize(300, 500);
        
        JPanel panel = new JPanel();
        panel.setSize(300, 500);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        add(panel);
        
        JLabel label = new JLabel("Status: Running");
        label.setLocation(10, 20);
        label.setSize(100,30);
        panel.add(label);
        
        JLabel labelPing = new JLabel("Server IP: " + IP);
        labelPing.setLocation(10, 60);
        labelPing.setSize(200,20);
        panel.add(labelPing);
        
        JLabel labelPort = new JLabel("Server Port: " + port);
        labelPort.setLocation(10, 80);
        labelPort.setSize(200,20);
        panel.add(labelPort);
        
        labelPlayers = new JLabel[count];
        boxPlayers = new JCheckBox[count];
        
        for(int i=0; i<count; i++){
            labelPlayers[i] = new JLabel("player " + (i+1));
            labelPlayers[i].setLocation(10, 160 + i*30);
            labelPlayers[i].setSize(100,20);
            panel.add(labelPlayers[i]);
            
            boxPlayers[i] = new JCheckBox("Connected", false);
            boxPlayers[i].setLocation(120, 160 + i*30);
            boxPlayers[i].setSize(100,20);
            boxPlayers[i].setEnabled(false);
            panel.add(boxPlayers[i]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String[] args){
        new StatusForm(2,"192.168.1.35",1835).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
