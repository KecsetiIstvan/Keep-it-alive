package com.example.kecseti.kislenyprojekt.Runable;

import android.content.Context;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kecseti.kislenyprojekt.Controls.LevelDisplay;
import com.example.kecseti.kislenyprojekt.Controls.MenuElemContainer;
import com.example.kecseti.kislenyprojekt.Flappy.FlappyView;

public class FlappyRun {
    private Handler kezelo;
    private long updateDelay;
    private Context c;
    private FlappyView flappyView;
    private ProgressBar moral;
    private MenuElemContainer menu;
    private LevelDisplay levelDisplay;

    public FlappyRun(Context c, FlappyView s, ProgressBar m, MenuElemContainer me, long u,LevelDisplay ld){
        this.c=c;
        this.flappyView=s;
        this.updateDelay=u;
        this.moral=m;
        this.menu=me;
        this.levelDisplay=ld;
        kezelo=new Handler();
    }

    //Eljndítunk egy szálat
    public void fuss(){
        kezelo.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Amennyiben még folyik a játék akkor uodatelunk rajta és rekurzívan ujrahívjuk
                if(!flappyView.didLost()) {
                    flappyView.update();
                    kezelo.postDelayed(this, updateDelay);
                }
                //Amennyiben a játékos vesztett akkor a megfelelő mutatót frissítjük, majd kiirunk egy üzenetet a megszerzett pontokkal
                // s megjelenítsük a menüt
                else{
                    if(moral.getProgress()<100){
                        moral.setProgress(moral.getProgress()+flappyView.getPont());
                    }
                    else{
                        moral.setProgress(0);
                        levelDisplay.incLevel();
                        levelDisplay.updateLevel();
                    }
                    final Toast toast = Toast.makeText(c, "Vesztettél! Pontjaid: "+flappyView.getPont(), Toast.LENGTH_LONG);
                    toast.show();
                    menu.show();
                }
            }
        },updateDelay);
    }
}
