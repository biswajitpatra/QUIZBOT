package com.bit.quizbot;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class socketconn extends AsyncTask<String,Void,Void> {
    //TODO: give ip and port
    String ip="192.168.43.54";
    int port=12345;
    @Override
    protected Void doInBackground(String... string) {
        try {
            Log.e(":::","Data to send to computer:"+string[0]);
            Socket s= new Socket(ip,port);
            PrintWriter pw= new PrintWriter(s.getOutputStream());
            DataInputStream dis=new DataInputStream(s.getInputStream());
            pw.format(String.valueOf('M'));
            pw.flush();
            pw.format(string[0]);
            pw.close();
            dis.close();
            pw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
