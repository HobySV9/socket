
package socket;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import insert.*;

import java.lang.management.*;
import java.io.*;

public class Client {

     public void runClient(int port,String ip){
        try {
            
            // System.out.println(ip);
            // System.out.println(port);
            Socket socketClient=new Socket(ip, port);
            ObjectOutputStream oos=new ObjectOutputStream(socketClient.getOutputStream());
            
            Vector<String> vs=new Vector<String>();
            vs.add(Client.getOS());
            vs.add(Client.NomUser()); 
            vs.add(Client.Ip());
            vs.add(Client.getHDDsize());
            vs.add(Client.getHDDfree());
            vs.add(Client.ramTotal());
            vs.add(Client.ramLibre());
            
            oos.writeObject(vs );
            oos.flush();
            oos.close();
            socketClient.close();
        } catch (Exception ex) {
            ex.getMessage();
        }



     }

    public int ClientPrendPort(InsertServer IT){
        return IT.getPort();
    }
    public String makaIp(InsertServer IT){
        return IT.getServerAdress();
    }



    public static String getOS(){
         String name = System.getProperty("os.name");
        String Version = System.getProperty("os.version");
       return name+"   "+Version;
        
    }
    public static String NomUser(){
         String utilisateur = System.getProperty("user.name");
         return utilisateur;
    
    }
      public static String Ip() throws Exception{
        InetAddress IA= InetAddress.getLocalHost();
            return IA.getHostAddress();
    }
    public static String getHDDsize(){
        File diskPartition = new File("/") ;
 
        long totalCapacity = diskPartition.getTotalSpace()  / (1024*1024); 

        String j = new String();
        
        j = j.valueOf(totalCapacity);
        
       
        return j+"  Mo";
 
    }

    public static String getHDDfree(){
        File diskPartition = new File("/") ;
 
        long freePartitionSpace = diskPartition.getFreeSpace()  / (1024*1024); 
 
        String j = new String();
 
        j = j.valueOf(freePartitionSpace);
 
        return j+"  Mo";
 
    }

    public static String ramTotal(){
        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        return memorySize+" Bytes";
    } 
    public static String ramLibre(){
        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
        return memorySize+" Bytes";
    }

    public static void main(String[] args) {

        InsertServer IT=new InsertServer("CLIENT",30,350);

        String i=new String();
        i="e";
        int p=0;

        Client c = new Client();


        while(true){
            
            i=IT.getServerAdress();
            p=IT.getPort();
            

            System.out.println(p);
            System.out.println(i);
            if(p>999  ){
                break;
            }

        }

        
            System.out.println(p+1);
            System.out.println(i+" gggggg ");


        if(p>999){
                while (true) {
                    c.runClient(p,i); 
                    
             System.out.println(p+2);
             System.out.println(i+" okok");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.getMessage();
                                }
            
                }   

            
        }
    

    }

 
    
    
}
