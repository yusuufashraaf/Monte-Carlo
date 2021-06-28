/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class MonteCarloMain extends JFrame implements ActionListener {
    public static JLabel index,days,start;
    public static JTextField index1,days1,start1;
    public static JButton submit;
    public static JComboBox combo;
    public MonteCarloMain(){
         JFrame main=new JFrame();
     //we use this instead of main to make the program work
        this.setLocation(400, 140);
        this.setResizable(false);
        this.setSize(600, 400);
        this.setTitle("simulation Program");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting component to frame
         //adding component to project
          index=new JLabel("Index");
          index.setBounds(40, 40, 50, 25);
          index.setForeground(Color.white);
          start=new JLabel("Start of duration");
          start.setBounds(200, 93, 110, 25);
          start.setForeground(Color.white);
          days=new JLabel("Duration");
          days.setBounds(300, 40, 80, 25);
          days.setForeground(Color.white);
          index1=new JTextField(20);
          index1.setBounds(37, 60, 160, 25);
          days1=new JTextField(20);
          days1.setBounds(295, 60, 160, 25);
          submit=new JButton("Submit");
          submit.setBounds(209,160,80,25);
          submit.addActionListener(this);
          start1=new JTextField();
          start1.setBounds(172,115,160,25);
          combo=new JComboBox();
          combo.setBounds(470,60,120,25);
          combo.addItem("Frequency");
          combo.addItem("Probability");
      //adding component to frame
       add(index);
       add(days);
       add(index1);
       add(days1);
       add(submit);
       add(combo);
       add(start);
       add(start1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit ){
            MonteCarlo s=new MonteCarlo();
            dispose();
            s.setVisible(true);
        }
    }
}
