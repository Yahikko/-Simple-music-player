package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageView playPauseIcon;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPauseIcon = findViewById(R.id.playIcon);

        mediaPlayer = MediaPlayer.create(this, R.raw.lofi);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //Для постоянного действия
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 500); /*c 0 cекунды с периодичностью в 0,5 секунды*/

    }

    public void play(View view) {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.baseline_play_arrow_24);
        }
        else {
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.baseline_pause_24);
        }
    }

    public void previous(View view) {
        seekBar.setProgress(0);
        mediaPlayer.seekTo(0);
        mediaPlayer.pause();
        playPauseIcon.setImageResource(R.drawable.baseline_play_arrow_24);
    }

    public void next(View view) {
        seekBar.setProgress(mediaPlayer.getDuration());
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        mediaPlayer.pause();
        playPauseIcon.setImageResource(R.drawable.baseline_play_arrow_24);
    }
}