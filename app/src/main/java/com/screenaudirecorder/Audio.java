package com.screenaudirecorder;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.widget.Toast;

import java.io.IOException;

public class Audio {

    private MediaRecorder myAudioRecorder;
    private String outputFile;
    private Context ctx;
    public int estado;


    public Audio(Context ctx) {
        this.ctx = ctx;
        estado = 0;
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recordingAudio.3gp";
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.reset();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);
    }

    public void record(){
        estado = 1;
        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
        } catch (IllegalStateException ise) {
            // make something ...
        } catch (IOException ioe) {
            // make something
        }
        //record.setEnabled(false);
        //stop.setEnabled(true);
        Toast.makeText(ctx, "Recording started AUDIO", Toast.LENGTH_LONG).show();
    }

    public void stop(){
        estado = 0;
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder = null;
        //record.setEnabled(true);
        //stop.setEnabled(false);
        //play.setEnabled(true);
        Toast.makeText(ctx, "Audio Recorder successfully", Toast.LENGTH_LONG).show();
    }

    public void play(){
        estado = 2;
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(outputFile);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(ctx, "Playing Audio", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // make something
        }
    }
}
