package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ClientHandler2 extends Thread {

    final Socket s;
    final DataInputStream dis;
    final DataOutputStream dos;


    public  ClientHandler2(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    public void run(){
        try {
            String str;
            String[] val = new String[2];
            while (true) {
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                String op = dis.readUTF();
                dos.writeUTF("Ok");
                switch (op) {
                    case "porcentagem":
                        str = dis.readUTF();
                        val = str.trim().split(" ");
                        double porcentagem = (Double.parseDouble(val[1])/100);
                        dos.writeUTF(String.valueOf(porcentagem));
                        str = dis.readUTF();
                        val = str.trim().split(" ");
                        double resulPorcentagem = (Double.parseDouble(val[1])/100) * Double.parseDouble(val[0]);
                        dos.writeUTF(String.valueOf(resulPorcentagem));
                        break;
                    case "raiz":
                        str = dis.readUTF();
                        str = str.trim();
                        double raiz = Math.sqrt(Double.parseDouble(str));
                        dos.writeUTF(String.valueOf(raiz));
                        break;
                    case "potencia":
                        str = dis.readUTF();
                        val = str.trim().split(" ");
                        double potencia = Math.pow(Double.parseDouble(val[0]), Double.parseDouble(val[1]));
                        dos.writeUTF(String.valueOf(potencia));
                        break;
                }

                s.close();
            }
        }
        catch (Exception err){
            System.err.println(err);
        }

    }
}
