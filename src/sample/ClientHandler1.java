package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ClientHandler1 extends Thread{
final Socket s;
final DataInputStream dis;
final DataOutputStream dos;


    //construtor
public ClientHandler1(Socket s, DataInputStream dis, DataOutputStream dos) {
    this.s = s;
    this.dis = dis;
    this.dos = dos;
}


public void run(){
    try {
     //   ServerSocket s = new ServerSocket(9998);
        String str, resut = "";
        String[] val = new String[3];
        while (true) {

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            str = dis.readUTF();
            val = str.trim().split(" ");

            double v1,v2;
            switch (val[1]) {
                case "+":
                    v1 = Double.parseDouble(val[0]);
                    v2 = Double.parseDouble(val[2]);
                    resut = String.valueOf(v1 + v2);
                    break;

                case "-":
                    v1 = Double.parseDouble(val[0]);
                    v2 = Double.parseDouble(val[2]);
                    resut = String.valueOf(v1 - v2);
                    break;

                case "*":
                    v1 = Double.parseDouble(val[0]);
                    v2 = Double.parseDouble(val[2]);
                    resut = String.valueOf(v1 * v2);
                    break;

                case "/":
                    v1 = Double.parseDouble(val[0]);
                    v2 = Double.parseDouble(val[2]);;
                    if(v2 != 0) {
                        resut = String.valueOf(v1 / v2);

                    }else{
                        resut = "( X / 0 ) Não exite";
                    }
                    break;
            }
            dos.writeUTF(resut);
            s.close();
        }
    }
    catch (Exception err){
        System.err.println(err);
    }

}

}