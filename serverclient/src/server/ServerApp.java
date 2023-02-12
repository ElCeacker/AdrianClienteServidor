package server;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import shared.Constants;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.SERVER_PORT);

            
            while (true) {
                System.out.println("***********************");
                System.out.println("Esperando por cliente...");
                Socket clientSocket = serverSocket.accept();
                
                String msg = clientSocket.getLocalAddress().toString();
                System.out.println(msg);
                DataInputStream fromClientStream = new DataInputStream(clientSocket.getInputStream());
                String name = fromClientStream.readUTF();
                String clientMessage = fromClientStream.readUTF();
                System.out.println("El cliente " + name + " dice: " + clientMessage);

                if (clientMessage == "bye") {
                    System.out.println("====================");
                    System.out.println("Cerrando el servidor...");
                    clientSocket.close();
                    serverSocket.close();
                    break;
                }
            }


            

        } catch (Exception e) {
            System.out.println("F en el SERVIDOR!!!");
        }
    }
}
