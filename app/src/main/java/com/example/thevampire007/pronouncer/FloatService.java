package com.example.thevampire007.pronouncer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

import es.dmoral.toasty.Toasty;

/**
 * Created by thevampire007 on 3/2/2017.
 */

public class FloatService extends Service {
    private WindowManager windowManager;
    private View mFloatWindow;
    Context context;
    TextToSpeech tts;
    boolean expanded = false;
    public FloatService()
    {

    }
    @Override
    public void onCreate() {
        super.onCreate();
        tts= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR) {
                    Toasty.info(getApplicationContext(), "TTS Engine is Starting!", Toast.LENGTH_SHORT, true).show();
                    tts.setLanguage(Locale.US);

                }
                else
                {
                    Toasty.info(getApplicationContext(),"Something Wrong with Your TTS Engine!").show();
                }
            }
        });
        mFloatWindow= LayoutInflater.from(this).inflate(R.layout.layout_floating_window,null);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity= Gravity.LEFT | Gravity.TOP;
        params.x=0;
        params.y=100;
        windowManager =(WindowManager)getSystemService(WINDOW_SERVICE);
        windowManager.addView(mFloatWindow,params);


        //The root element of the collapsed view layout
        final View collapsedView = mFloatWindow.findViewById(R.id.collapse_view);
        //The root element of the expanded view layout
        final View expandedView = mFloatWindow.findViewById(R.id.expanded_view);


        //Set the close button
        final ImageView closeButtonCollapsed = (ImageView) mFloatWindow.findViewById(R.id.close_btn);
        closeButtonCollapsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //close the service and remove the from from the window
                stopSelf();
            }
        });
        final EditText f_et=(EditText)mFloatWindow.findViewById(R.id.et_ft);
        f_et.setFocusable(true);
        f_et.setFocusableInTouchMode(true);
        Button bspeak_ft = (Button)mFloatWindow.findViewById(R.id.bspeak_ft);
        bspeak_ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(f_et.getText().toString(), TextToSpeech.QUEUE_FLUSH,null,null);
            }
        });





        mFloatWindow.findViewById(R.id.collapse_view).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;


                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);


                        //Update the layout with new X & Y coordinate
                        windowManager.updateViewLayout(mFloatWindow, params);
                        return true;
                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);


                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (Xdiff < 10 && Ydiff < 10) {
                            if (mFloatWindow.findViewById(R.id.expanded_view).getVisibility()==View.GONE) {
                                //When user clicks on the image view of the collapsed layout,
                                //visibility of the collapsed layout will be changed to "View.GONE"
                                //and expanded view will become visible.
                                //collapsedView.setVisibility(View.GONE);
                                expandedView.setVisibility(View.VISIBLE);
                            }
                           // if(mFloatWindow.findViewById(R.id.expanded_view).getVisibility()==View.VISIBLE) This line did'nt workded and i have no idea why
                            else{
                                expandedView.setVisibility(View.GONE);
                            }
                        }
                        return true;
                }
                return false;
            }
        });

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatWindow != null) windowManager.removeView(mFloatWindow);
    }
}
