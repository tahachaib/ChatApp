package com.example.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class s2 {
    @FXML
    private TextField message ;
    @FXML
    private TextField HostID;
    @FXML
    private TextField PortID;

    @FXML
    private ListView<String> testview;
    PrintWriter pw ;

    @FXML

    protected void onconnect() throws Exception {

        String host=HostID.getText();
        int port = Integer.parseInt(PortID.getText());
        Socket s = new Socket(host,port);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = s.getOutputStream();
        pw = new PrintWriter(os,true);
        new Thread(()-> {
            while (true){
                try {
                    String reponse = br.readLine();
                    Platform.runLater(()->{
                        testview.getItems().add(reponse);
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        }).start();

    }
@FXML
    public void onsubmit(){
        String msg=message.getText();
        pw.println(msg);

}













}
