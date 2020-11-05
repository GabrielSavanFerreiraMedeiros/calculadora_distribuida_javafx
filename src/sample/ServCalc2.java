package sample;// Java implementation of  Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.util.*;
import java.net.*;

// Server class
public class ServCalc2
{
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(9999);

        Scanner scn = new Scanner(System.in);

        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket s = null;

            try {

                // socket object to receive incoming client requests

                s = ss.accept();

                System.out.println("inicializei o servidor");
                System.out.println("Um novo cliente está conectado: " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Atribuindo novo thread para este cliente");

                // create a new thread object
                Thread t = new ClientHandler2(s, dis, dos);

                //startando thread
                t.start();



            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }



    }
}