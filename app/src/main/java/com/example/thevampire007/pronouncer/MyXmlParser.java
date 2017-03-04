package com.example.thevampire007.pronouncer;

import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Created by thevampire007 on 3/1/2017.
 */
/*
public class MyXmlParser {
    public StringBuffer parse(StringBuffer xml, String req_tag) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(xml.toString()));
        int eventType = parser.getEventType();
        StringBuffer response= new StringBuffer();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_TAG)
            {
                if(parser.getName().toString().equalsIgnoreCase("def"))
                {
                   response.append(parser.getText());
                    response.append("\n");

                    Log.i("parse",response.toString());
                }
            }
            eventType = parser.next();
        }
        return response;
    }
}
*/