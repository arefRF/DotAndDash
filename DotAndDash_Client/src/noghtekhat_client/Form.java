
package noghtekhat_client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Form extends javax.swing.JFrame {

    Map map;
    JPanel panel;
    JPanel scorepanel;
    JLabel scoreLabel;
    int dummy;
    int selected;
    boolean isTurn;
    JLabel turnLabel;
    public Form(int count, Map map) {
        this.map = map;
        isTurn = false;
        dummy = 0;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        this.setSize(800, 650);
        panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        add(panel);
        
        for(int i=0; i<count-1; i++){
            for(int j=0; j<count; j++){
                map.lines_v[i][j] = new Button();
                map.lines_v[i][j].setSize(7,50);
                map.lines_v[i][j].setText("");
                map.lines_v[i][j].setLocation(j*50 + ((j)*7), i*50 + ((i+1)*7));
                panel.add(map.lines_v[i][j]);
                map.lines_v[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        buttonActionPerformed(evt);
                   }
                });
                
                map.Lines.add(map.lines_v[i][j]);
            }
        }
        for(int j=0; j<count-1; j++){
            for(int i=0; i<count; i++){
                map.lines_h[i][j] = new Button();
                map.lines_h[i][j].setSize(50,7);
                map.lines_h[i][j].setText("");
                map.lines_h[i][j].setLocation(j*50 + ((j+1)*7), i*50 + ((i)*7));
                
                panel.add(map.lines_h[i][j]);
                map.lines_h[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        buttonActionPerformed(evt);
                   }
                });
                map.Lines.add(map.lines_h[i][j]);
            }
        }
        
        for(int i=0; i<map.size-1; i++){
            for(int j=0; j<map.size-1; j++){
                map.boxes[i][j] = new Box(map.lines_v[i][j], map.lines_v[i][j+1], map.lines_h[i][j], map.lines_h[i+1][j]);
            }
        }
        
        scorepanel = new JPanel();
        scorepanel.setBackground( Color.green );
        scorepanel.setSize(200, 600);
        scorepanel.setLocation(600, 0);
        scorepanel.setLayout(null);
        add(scorepanel);
        
        scoreLabel = new JLabel();
        scoreLabel.setSize(100,50);
        scoreLabel.setLocation(10, 100);
        scoreLabel.setText("Score: 0");
        scorepanel.add(scoreLabel);
        
        turnLabel = new JLabel();
        turnLabel.setSize(100,50);
        turnLabel.setLocation(10, 150);
        turnLabel.setText("Wait");
        scorepanel.add(turnLabel);
        
    }
    
    private void buttonActionPerformed(java.awt.event.ActionEvent evt){
        if(!isTurn){
            for(int i=0; i<map.Lines.size(); i++)
                if(map.Lines.get(i).isSelected())
                    map.Lines.get(i).setSelected(false);
        }
        else{
            for(int i=0; i<map.Lines.size(); i++){
                if(map.Lines.get(i).isSelected()){
                    selected = i;
                    map.Lines.get(i).isSelected = true;
                    map.Lines.get(i).setSelected(false);
                    map.Lines.get(i).setEnabled(false);
                    map.Lines.get(i).setBackground(map.playerColor);

                }
            }
            dummy = 1;
            LinkedList<Point> list = map.CheckBoxCreated();
            Draw(list, panel.getGraphics(), map.playerColor);
            isTurn = false;
            turnLabel.setText("Wait");
        }
    }
    
    private void Draw(LinkedList<Point> list, Graphics g, Color color){
        for(int i=0; i<list.size(); i++){
            System.out.println(color);
            g.setColor(Color.RED);
            g.fillRect(map.boxes[list.get(i).x][list.get(i).y].v1.getLocation().x + 9, map.boxes[list.get(i).x][list.get(i).y].v1.getLocation().y + 3, 45, 45);
            g.setColor(Color.RED);
            map.score++;
        }
        UpdateScore();
    }
    
    private void UpdateScore(){
        scoreLabel.setText("Score: " + map.score);
        if(map.isFinished()){
            JOptionPane.showMessageDialog(this, "Score: " + map.score);
            this.dispose();
        }
    }
    
    public void UpdateFromServer(int i, Color color){
        map.Lines.get(i).isSelected = true;
        map.Lines.get(i).setSelected(false);
        map.Lines.get(i).setEnabled(false);
        map.Lines.get(i).setBackground(color);
        
        LinkedList<Point> list = map.CheckBoxCreated();
        map.score -= list.size();
        Draw(list, panel.getGraphics(), color);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(407, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}

class Button extends JToggleButton{
    public boolean isSelected;
    
    public Button(){
        super();
        isSelected = false;
    }
}