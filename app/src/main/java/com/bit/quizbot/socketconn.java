package com.bit.quizbot;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class socketconn extends AsyncTask<String,Void,Void> {
    //TODO: give ip and port
    String ip="1231";
    int port=1234;
    @Override
    protected Void doInBackground(String... string) {
        try {
            Socket s= new Socket(ip,port);
            PrintWriter pw= new PrintWriter(s.getOutputStream());
            DataInputStream dis=new DataInputStream(s.getInputStream());
            pw.format(string.toString());
            
            dis.close();
            pw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
