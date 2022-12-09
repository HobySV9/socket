import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
 
        public static void main(String[] test) {
 
                final ServerSocket serveurSocket ;
                final Socket kil ;
                final PrintWriter out;
                final BufferedReader in;
                final Scanner entrer=new Scanner(System.in);
 
                try {
                        serveurSocket = new ServerSocket(4242);
                        kil = serveurSocket.accept();
                        out = new PrintWriter(kil.getOutputStream());
                        in = new BufferedReader (new InputStreamReader (kil.getInputStream()));
                        Thread envoi= new Thread(new Runnable() {
                        String msg;
 
                public void run() {
                        while(true){
                                msg = entrer.nextLine();
                                out.println(msg);
                                out.flush();
                        }
                }
        }
        );
        envoi.start();
 
        Thread recevoir= new Thread(new Runnable() {
        String msg ;
 
                public void run() {
                try {
                        msg = in.readLine();
                        
                        while(msg!=null){
                                System.out.println("User1 : "+msg);
                                msg = in.readLine();
                        }
                        
                        System.out.println("User1 déconecté");
                        
                        out.close();
                        kil.close();
                        serveurSocket.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                }
                });
                recevoir.start();
                }catch (IOException e) {
                e.printStackTrace();
                }
        }
}