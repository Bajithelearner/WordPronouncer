package com.example.thevampire007.pronouncer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.jar.Manifest;

/**
 * Created by thevampire007 on 3/1/2017.
 */

/*public class AsyncMeaning extends AsyncTask<String,Void,StringBuffer> {
    String myapikey="daffb45a-62ee-4651-b5f2-4842462de742";
    String base_url="http://www.dictionaryapi.com/api/v1/references/collegiate/xml/";
    String meaning;
    InputStream in;
    StringBuffer response;
    StringBuffer meaninglist;
    Context _context;
    public AsyncMeaning(Context c)
    {
        _context=c;
    }

    @Override
    protected StringBuffer doInBackground(String... params) {
        String main_url=base_url+params[0]+"?key="+myapikey;
        try{
            URL url= new URL(main_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK)
            {
                in= httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line="";
                response= new StringBuffer();
                meaninglist= new StringBuffer();
                while((line=bufferedReader.readLine())!=null)
                {
                    response.append(line);
                }
                MyXmlParser myXmlParser = new MyXmlParser();
                 meaninglist= myXmlParser.parse(response,"dt");
            }
            else
            {
                Log.i("NetworkTag","Network Error");
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            if(in!=null)
            {   try {
                in.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            }
        }
     return meaninglist;
    }

    @Override
    protected void onPostExecute(StringBuffer s) {
        super.onPostExecute(s);
        MainActivity mainActivity = (MainActivity)_context;
        mainActivity.meaning=s;
    }
}*/
