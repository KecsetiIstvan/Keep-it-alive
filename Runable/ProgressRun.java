package com.example.kecseti.kislenyprojekt.Runable;

import android.os.Handler;
import android.widget.ProgressBar;

import com.example.kecseti.kislenyprojekt.Controls.LevelDisplay;

public class ProgressRun {
    private Handler kezelo;
    private int refresh;
    private ProgressBar progress;
    private int amount;
    private LevelDisplay levelDisplay;

    public ProgressRun(int refresh, ProgressBar hunger,int amount,LevelDisplay ld){
        kezelo=new Handler();
        this.refresh=refresh;
        this.progress=hunger;
        this.amount=amount;
        this.levelDisplay=ld;
    }
    public void fuss(){
        kezelo.postDelayed(new Runnable() {
            @Override
            public void run() {
               progress.setProgress(progress.getProgress()-amount);
                if(progress.getProgress()<=0){
                    progress.setProgress(99);
                    levelDisplay.decLevel();
                    levelDisplay.updateLevel();
                }
               kezelo.postDelayed(this, refresh);
            }
        },refresh);

    }
}
