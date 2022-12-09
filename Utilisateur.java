import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Utilisateur {
    public static void main(String[] args) {
        final Socket kil;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner entrer = new Scanner(System.in);
 
                try {
                    kil = new Socket("127.0.0.1",4242);
                    out = new PrintWriter(kil.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(kil.getInputStream()));
 
                Thread envoyer = new Thread(new Runnable() {
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
 
                envoyer.start();
 
                Thread recevoir = new Thread(new Runnable() {
                    String msg;
 
                    public void run() {
                        try {
                            msg = in.readLine();
                            while(msg!=null){
                                System.out.println("Serveur : "+msg);
                                msg = in.readLine();
                            }
                            System.out.println("Serveur déconecté");
                            out.close();
                            kil.close();
                        } catch (IOException e) {
                        e.printStackTrace();
                        }
                    }
                });
                
                recevoir.start();
 
                    } catch (IOException e) {
                    e.printStackTrace();
                        }
    }  
}