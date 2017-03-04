package com.example.thevampire007.pronouncer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Locale;
import java.util.jar.Manifest;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button bspeak,bfloat;
    //Button bmeaning;
     TextToSpeech tts;
    public int CODE_DRAW_OVER_OTHER_APP_PERMISSION=10;
    Context activity;
    private final static int per_req_code=10;
    //public StringBuffer meaning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       activity =getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et= (EditText) findViewById(R.id.et);
        bspeak= (Button) findViewById(R.id.bspeak);
       // bmeaning= (Button) findViewById(R.id.bmeaning);
        //meaning=new StringBuffer();
        bfloat= (Button) findViewById(R.id.bfloat);
        //TTS Intilization
        tts= new TextToSpeech(activity, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR) {
                    Toasty.info(activity, "TTS Engine is Starting!", Toast.LENGTH_SHORT, true).show();
                    tts.setLanguage(Locale.US);

                }
                else
                {
                    Toasty.info(activity,"Something Wrong with Your TTS Engine!").show();
                }
            }
        });

        //Check if the application has draw over other apps permission or not?
        //This permission is by default available for API<23. But for API > 23
        //you have to ask for the permission in runtime.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {


            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        } else {
            initializeView();
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
                initializeView();
            } else { //Permission is not available
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Set and initialize the view elements.
     */
    private void initializeView() {
       //getthewindow(bfloat);
    }
    public void speak(View v)
    {
        if(et.getText().length()>0)
        {
            tts.speak(et.getText(), TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else
        {
            Toasty.error(activity,"You Need to Enter Something First!").show();
        }
    }
    /* public void getthemeaning(View v)
    {
        if(et.getText().length()>0)
        {
            AsyncMeaning asyncMeaning= new AsyncMeaning(this);
            asyncMeaning.execute(et.getText().toString());
        }
        else
        {
            Toasty.error(activity,"You Need to Enter Something First!").show();
        }
    }*/
    public void getthewindow(View v)
    {
        Intent floatIntent = new Intent(this,FloatService.class);
            startService(floatIntent);
    }

    public void onPause()
    {
        super.onPause();
        tts.shutdown();
    }

}
