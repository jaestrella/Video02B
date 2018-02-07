package com.iesvirgendelcarmen.dam.video02b;

import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.PixelCopy;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class Video02B extends AppCompatActivity implements SurfaceHolder.Callback{
    Uri pelicula;
    TextView titulo;

    MediaPlayer reproductor;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean pausa = false;
    String video= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Cuerdas.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video02_b);

        Button botonPlay=(Button)findViewById(R.id.reproduce);
        Button botonPausa=(Button)findViewById(R.id.pausa);
        titulo=(TextView)findViewById(R.id.video);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView=(SurfaceView)findViewById(R.id.surface);
        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(176, 144);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        reproductor=new MediaPlayer();

        botonPlay.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                pausa=false;
                if(reproductor.isPlaying()){
                    reproductor.reset();
                }
                reproductor.setAudioStreamType(AudioManager.STREAM_MUSIC);
                reproductor.setDisplay(surfaceHolder);
                try{
                    reproductor.setDataSource(video);
                    reproductor.prepare();
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
                reproductor.start();
            }
        });

        botonPausa.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(pausa){
                    pausa=false;
                    reproductor.start();
                }else{
                    pausa=true;
                    reproductor.pause();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reproductor.release();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
