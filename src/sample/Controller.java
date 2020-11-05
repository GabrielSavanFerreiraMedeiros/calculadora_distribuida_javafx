package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Label display, display1;
    @FXML
    public BorderPane borderPane;

    public String v1 = "", v2 = "";
    public  String op;
    public  String memo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTimeout(() -> {
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::fecharJanela);
        }, 1000);
    }


    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    public void numeros(MouseEvent event){
        display.setText("");
        String id = ((Control)event.getSource()).getId();
        String val = "";

        switch (id){
            case "um":
                val = "1";
                break;
            case "dois":
                val = "2";
                break;
            case "tres":
                val = "3";
                break;
            case "quatro":
                val = "4";
                break;
            case "cinco":
                val = "5";
                break;
            case "seis":
                val = "6";
                break;
            case "sete":
                val = "7";
                break;
            case "oito":
                val = "8";
                break;
            case "nove":
                val = "9";
                break;
            case "zero":
                val = "0";
                break;
            case "ponto":
                if(v1 == ""){
                    val = "0.";
                }else{
                    val = ".";
                }

                break;
            }
        //regra

        if (op == null){
        // preencher o valor 1
        v1 += val;
        display.setText(v1);

        }else {
            // preencher valor 2

            v2 += val;
            display.setText(v2);
            memo = v1 + " " + op + " " + v2;
        }

    }

    public void operacao(MouseEvent event) {
        String id = ((Control) event.getSource()).getId();
        display.setText("");

        if (v1 != null ) {
            switch (id) {
                case "somar":
                    op = "+";
                    display1.setText(v1 + "+");
                    break;
                case "subtrair":
                    op = "-";
                    display1.setText(v1 + "-");
                    break;                              // momo para continuar o calculo
                case "multiplicar":
                    op = "*";
                    display1.setText(v1 + "x");
                    break;
                case "dividir":
                    op = "/";
                    display1.setText(v1 + "/");
                    break;
                case "pot":
                    op = "^";
                    display1.setText(v1 + "^");
                    break;
            }
        }
    }



    public void porcento() throws IOException {
        Socket s2 = new Socket("127.0.0.1", 9999);
        DataInputStream dis = new DataInputStream(s2.getInputStream());
        DataOutputStream dos = new DataOutputStream(s2.getOutputStream());
        dos.writeUTF("porcentagem");
        if (dis.readUTF().trim().equals("Ok")) {
            String aux = v1 + " " + v2;
            System.out.println(v1 + "" + v2);
            dos.writeUTF(aux);
            v2 = dis.readUTF();// primeira chamada ao servido para mostra o que esta sendo calculardo
            display1.setText(v1 + op + v2);
            dos.writeUTF(aux);
            v1 = dis.readUTF();// segundo chamada para trazer resultado
            display.setText(v1);
            v2 = "";
            op = ""; // recebe um string vazia ao invés de null para não entrar no if da linha 92

        }
    }

    public void raiz() throws  IOException {
        Socket s2 = new Socket("127.0.0.1", 9999);
        DataInputStream dis = new DataInputStream(s2.getInputStream());
        DataOutputStream dos = new DataOutputStream(s2.getOutputStream());
        dos.writeUTF("raiz");
        if (dis.readUTF().trim().equals("Ok")) {
            dos.writeUTF(v1);
            memo = dis.readUTF();
            display.setText(memo);
            display1.setText(v1+"√");
            v1 = memo;// é atribuido o resultado ao valor 1
        }
    }

    public void potencia() throws IOException {
        display.setText("");
        Socket s2 = new Socket("127.0.0.1", 9999);
        DataInputStream dis = new DataInputStream(s2.getInputStream());
        DataOutputStream dos = new DataOutputStream(s2.getOutputStream());
        dos.writeUTF("potencia");
        if (dis.readUTF().trim().equals("Ok")){
            String aux = v1 + " " + v2;
            display1.setText(v1 + "^" + v2);
            dos.writeUTF(aux);
            memo = dis.readUTF();
            v1 = memo;
            display.setText(memo);
            v2 = "";
            op = "";// recebe um string vazia ao invés de null para não entrar no if da linha 92

        }
    }

    public void igual() throws IOException {
        if (op.trim().equals("^")) {
            potencia();
        } else {
            Socket s1 = new Socket("127.0.0.1", 10000);
            DataInputStream dis1 = new DataInputStream(s1.getInputStream());
            DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());
            if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
                dos1.writeUTF(memo);
                String res = dis1.readUTF();
                display1.setText(res);
                v1 = res;
                v2 = "";
                display.setText("");
            }
        }

    }

    public void limpar(MouseEvent event) {
        String id = ((Control) event.getSource()).getId();
        if (id.equals("c")){
        display.setText("");
        display1.setText("");
        v1 = "";
        v2 = "";
        op = null;

        }
    }

    public  void fecharJanela(WindowEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção");
        alert.setHeaderText("");
        alert.setContentText("A calculadora foi finalizada");

        alert.showAndWait();
    }
}
