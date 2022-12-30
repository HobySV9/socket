
package socket;

import insert.*;
import javax.swing.*;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Dimension;
import java.net.InetAddress;


public class Server {
    
    public static Socket creatServer(int port)throws Exception{
        ServerSocket server=new ServerSocket(port);
        Socket socket=server.accept();
        return socket;
    }


    public void runServer(int xxxx){      
        
        
        JFrame fenetre = new JFrame();
        fenetre.setTitle("server");
        fenetre.setSize(1000, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        Vector<Vector> data = new Vector<Vector>();

        Vector<Integer> vip = new Vector<Integer>();
        vip.addElement(0);
        vip.addElement(0);

        int isa=0;
        int nbr=0;
        int cmt=0;
        
        while(true){
            try {
                isa=0;cmt=0;nbr=0;
                Socket socket1 = creatServer(xxxx);
                
                ObjectInputStream ois=new ObjectInputStream(socket1.getInputStream());
                
                Vector<String> colis = (Vector<String> ) ois.readObject();
                  
                SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
                
                Date date = new Date();
                
                colis.add(s.format(date));


                                            
                InetAddress IA= InetAddress.getLocalHost();
                String azerty=IA.getHostAddress();
                for(int i=0;i <azerty.length();i++){
                    if(azerty.charAt(i)=='.'){
                        cmt=i+1;
                    }
                
                }


                String sss=colis.get(2).substring(cmt);

                nbr=Integer.parseInt(sss);


                for(int u = 0; u<vip.size(); u++){
                     if(nbr==vip.get(u)){
                         isa=u;
                     }
                }

                if(isa==0){
                    vip.addElement(nbr);
                
                    data.addElement(colis);

                }
                     
                if(isa!=0){
                         data.set(isa-2,colis);
                }


                System.out.println(vip);
                System.out.println();

                     


                Vector<String> title = new Vector<String>();
                title.addElement("systeme d'expoitation ");
                title.addElement("utilisateur");
                title.addElement(" adresse Ip");
                title.addElement("taille partition locale Client");
                title.addElement("espace libre partition ");
                title.addElement("ram total");
                title.addElement("ram libre");
                title.addElement("HEURE");
                
                JTable tableau = new JTable(data, title);

  

                fenetre.setContentPane(new JScrollPane(tableau));
                fenetre.setVisible(true);
  

                socket1.close();
            
                } catch (Exception ex) {
                    ex.getMessage();
                }
            
        }
        


        
     
    }


    public int ServerPrendPort(InsertServer IT){
        return IT.getPort();
    }

    public static void main(String[] args) {
        
        InsertServer is=new InsertServer("SERVER",30,30);

        Server s= new Server();
        int pr=0;

        while(true){

            pr=s.ServerPrendPort(is);   


            if(pr>999){
                
                s.runServer(pr);
                
                break;
            }



        }
        
    }
    
    
    
}
