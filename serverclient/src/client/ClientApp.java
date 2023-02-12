package client;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import shared.Constants;

public class ClientApp {
    
    public static void main(String[] args) {

        try {
            
            while (true) {
                InetAddress myIp = InetAddress.getLocalHost();
                Socket socket = new Socket(myIp, Constants.SERVER_PORT);
                Scanner teclado = new Scanner(System.in);
    
                DataOutputStream toServerStream = new DataOutputStream(socket.getOutputStream());
                System.out.println("¿Cómo te llamas?");
                String name = teclado.nextLine();
                toServerStream.writeUTF(name);
                System.out.print("Mensaje para el servidor: ");
                String message = teclado.nextLine();
                toServerStream.writeUTF(message);
                
                if (message.equals("bye")) {
                    toServerStream.close();

                    break;
                }
                teclado.close();
                socket.close();
            }
        } catch (Exception e) {
            System.out.println("F en el CLIENTE!!!");
            System.out.println("ENCIENDE EL SERVIDOR");
        }
    }

}
