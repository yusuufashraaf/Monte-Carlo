
package MonteCarlo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class MonteCarlo extends JFrame implements ActionListener {
             public static JTextField[ ] tabTextFields;
             public static JTextField Average;
             public static JTextField Expected;
             public static JButton submit;
             public static DefaultTableModel model = new DefaultTableModel();
             public  static JTable table = new JTable( model );
             public static JScrollPane tableScroller = new JScrollPane( table );
             public static JButton back,clear; 
            
          public MonteCarlo(){
        JFrame main1=new JFrame();
        this.setLocation(400, 140);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setTitle("simulation Program");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //adding textfields and labels
        {
              submit=new JButton("Submit");
              submit.addActionListener(this);
        tabTextFields = new JTextField[MonteCarloFunctions.index()+1];
          int z=10,y=10;
       tabTextFields = new JTextField[MonteCarloFunctions.index()+1];
      for(int i=1;i<MonteCarloFunctions.index()+1;i++)
       {  
           JLabel l=new JLabel(MonteCarloFunctions.combo()+i);
           l.setForeground(Color.white);
        tabTextFields[i]=new JTextField();
        if(i%5==1)
        {z+=80;
        y=130;
        }
        tabTextFields[i].setBounds(0+y, 0+z, 100, 25);
        y+=130;  
        l.setBounds(y-120, z-20, 100, 25);
        submit.setBounds(y-120,z+50,80,25);
        add(tabTextFields[i]);
        add(l);
        add(submit);
       }
          }
        //adding table
        {
        table.setBackground(java.awt.Color.WHITE);
        tableScroller.setBounds(900, 40, 500, 500);
        String g=(String) MonteCarloMain.combo.getSelectedItem();
        model.addColumn(g);
        model.addColumn("cumulative probability");
        model.addColumn("Interval");
        model.addColumn("Days");
        model.addColumn("Randoms");
        model.addColumn("Simulated");
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.BLACK);
        add(tableScroller);
          }
        //adding back & clear button
         back=new JButton("Back");
         back.setBounds(0, 0, 80, 25);
         back.addActionListener(this);
         
         clear=new JButton("clear");
         clear.setBounds(1057, 680, 80, 25);
         clear.addActionListener(this);
         //average and expected
            JLabel average=new JLabel("Average Demand");
            average.setBounds(900, 600, 130, 25);
            average.setForeground(Color.white);
            JLabel expected=new JLabel("Expected daily demand");
            expected.setBounds(1200, 600, 180, 25);
            expected.setForeground(Color.white);
             Average=new JTextField();
             Average.setBounds(896, 620, 120, 25);
             Expected=new JTextField();
             Expected.setBounds(1200, 620, 120, 25);
    add(back);
    add(average);
    add(Average);
    add(expected);
    add(Expected);
    add(clear);

}
    public static void main(String[] args) {
        MonteCarloMain m=new MonteCarloMain();
         m.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==submit){
             model.setRowCount(0);
             String n=(String) MonteCarloMain.combo.getSelectedItem();
        if(n=="Frequency" ){
            MonteCarloFunctions.calculatefrequency();
            Average.setText(MonteCarloFunctions.averagefrequency()+"");
            Expected.setText(MonteCarloFunctions.expected()+"");
        }
        else{
            MonteCarloFunctions.calculateprobabilities();
            Average.setText(MonteCarloFunctions.averageprobability() + "");
            Expected.setText(MonteCarloFunctions.expected()+"");
        }
        }
         if(e.getSource()==back){
            model.setRowCount(0);
            model.setColumnCount(0);
            dispose();
            MonteCarloMain s=new MonteCarloMain();
            s.setVisible(true);
         }
         if(e.getSource()==clear){
             String x=MonteCarloMain.index1.getText();
             int y=Integer.parseInt(x);
             for(int i=1;i<y+1;i++){
                 MonteCarlo.tabTextFields[i].setText("");
             }
             MonteCarlo.Average.setText("");
             MonteCarlo.Expected.setText("");
             model.setRowCount(0);
             
         }
    }
}
