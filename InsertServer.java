package insert;

import socket.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.GridLayout;

import java.net.InetAddress;
import java.net.Socket;

public class InsertServer implements ActionListener{

  
    JTextField tf1;
    JTextField tf2;
    String serverAdress;
    int port;
    JButton accept;

    public JTextField getTf1(){
      return this.tf1;
    }

    public void setTf1(JTextField tf1){
        this.tf1 = tf1;
    }
    public JTextField getTf2(){
      return this.tf2;
    }

    public void setTf2(JTextField tf2){
        this.tf2 = tf2;
    }
    public String getServerAdress(){
      return this.serverAdress;
    }

    public void setServerAdress(String serverAdress){
        this.serverAdress = serverAdress;
    }
    public int getPort(){
      return this.port;
    }

    public void setPort(int port){
        this.port = port;
    }
    public JButton getAccept(){
      return this.accept;
    }

    public void setAccept(JButton accept){
        this.accept = accept;
    }

    public  String insertIp() throws Exception{
          InetAddress IA= InetAddress.getLocalHost();
          return IA.getHostAddress();
      }

  
  
    public InsertServer(String titre,int x,int y){
    
        JFrame content = new JFrame();
        content.setSize(300,300);
        content.setTitle(titre);
        content.setLocation(x,y);
        content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            
        JPanel panneau0 = new JPanel();
        panneau0.add(new JLabel(titre+":"));
            
        JPanel panneau1 = new JPanel();
        panneau1.add(new JLabel("port:     (>1000)"));

        this.setTf1(new JTextField("4242"));


        JPanel panneau2 = new JPanel();

          if(titre=="SERVER"){
            panneau2.add(new JLabel("voici l' adressIPduServer:"));

            try{
                JTextField ty=new JTextField(this.insertIp());
                ty.setEditable(false);
                this.setTf2(ty) ;
                }                                          
            catch (Exception ex) {
                  ex.getMessage();
            }

          }
          
          else{
              panneau2.add(new JLabel("adressIPduServer:"));
  
              this.setTf2(new JTextField("127.0.0.1")) ;
          }

        JButton accept = new JButton("ok");
            accept.addActionListener(this);
            this.setAccept(accept);

    content.setLayout(new GridLayout(6, 1));
    content.getContentPane().add(panneau0);
    content.getContentPane().add(panneau1);
    content.getContentPane().add(this.getTf1());
    content.getContentPane().add(panneau2);
    content.getContentPane().add(this.getTf2());
    content.getContentPane().add(this.getAccept());
    content.setVisible(true);
  }

    public void actionPerformed(ActionEvent e) {
        String text = this.tf1.getText();
        String text2 = this.tf2.getText();
        String end = "";
        String end2 = "";
        if(e.getSource() == this.accept){
          end = text;
          end2 = text2;
          tf1.setEditable(false);
          tf2.setEditable(false);

          this.setPort(Integer.parseInt(end));
          this.setServerAdress(end2);
    
        }
    }


}