package com.example.control;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class chatserver extends Thread{
    private int Clientnb ;
    private List<Communication> clients = new ArrayList<Communication>();


    public static void main(String[] args) {

        new chatserver().start();
    }
    @Override
    public void run(){
        try {
            ServerSocket ss = new ServerSocket(2022);
            System.out.println(" démarrage ... ");
            while (true){
                Socket s=ss.accept();
                ++Clientnb ;

                Communication newCommunication= new Communication(s,Clientnb);
                clients.add(newCommunication);
                newCommunication.start();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public class Communication extends Thread {
        private Socket s;
        private int clientnb;
        Communication(Socket s, int Clientnb ){
            this.clientnb = Clientnb;
            this.s=s ;
        }
        @Override
        public void run(){
            try {
                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = s.getOutputStream();
                System.out.println(" le client numéro " + Clientnb + " et son adresse ip est : " + s.getRemoteSocketAddress());

                PrintWriter pw = new PrintWriter(os,true);
                pw.println(" Vous étes le client :  " + clientnb);
                pw.println( " text ... ");
                while (true){
                    String UserRequest = br.readLine();
                    if (UserRequest.contains("=>")) {
                        String[] usermessage = UserRequest.split("=>");
                        if(usermessage.length == 2 ){
                            String msg = usermessage[1];
                            int numclient = Integer.parseInt(usermessage[0]);
                            Send(msg , s, numclient);

                        }
                    }
                    else {
                        Send(UserRequest , s, -1);
                    }



                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        void Send(String UserRequest , Socket sock , int nbre) throws IOException {
            for(Communication client : clients) {

                if (client.s != sock) {
                    if(client.clientnb==nbre || nbre ==-1 ) {
                        PrintWriter pw = new PrintWriter(client.s.getOutputStream(), true);
                        pw.println(UserRequest);
                    }
                }
            }
        }
    }
}
