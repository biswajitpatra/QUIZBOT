package com.bit.quizbot;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class networking {
    //TODO: finish this class
    public Void getHTML()
    {

        HttpURLConnection urlConnection = null;
        String result ="";
        int spaceCount,i;

        try {
            while(true) {
                URL url = new URL("https://cet-dc.herokuapp.com/client");
                urlConnection = (HttpURLConnection) url.openConnection();

                int code = urlConnection.getResponseCode();

                if (code == 200) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    if (in != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line = "";

                        while ((line = bufferedReader.readLine()) != null)
                            result += line;
                    }
                    in.close();
                }

                String arr="from ";
                spaceCount = 0;
                i=0;
                result=result.trim();
                while( i < result.length() ){
                    if( result.charAt(i) == ' ' ) {
                        arr=arr+"|"+Integer.toString(i);
                        spaceCount++;
                    }
                    i++;
                }


                if(spaceCount==2) {
                    Log.e("ALERT:::", result+" "+Integer.toString(spaceCount)+"|"+arr);
                    break;
                }
                result="";
                //Log.e("RESULT::: ",result);
            }

            Log.e("RESULT::: ",result);
            String[] words = result.split(" ");




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            urlConnection.disconnect();
        }
        return null;
    }
}
