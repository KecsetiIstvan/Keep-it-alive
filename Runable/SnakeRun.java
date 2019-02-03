package com.example.kecseti.kislenyprojekt.Runable;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kecseti.kislenyprojekt.Controls.LevelDisplay;
import com.example.kecseti.kislenyprojekt.Controls.MenuElemContainer;
import com.example.kecseti.kislenyprojekt.Snake.SnakeView;

import java.util.logging.Level;

public class SnakeRun {

    private Handler kezelo;
    private long updateDelay;
    private Context c;
    private SnakeView snakeView;
    private ProgressBar ehseg;
    private MenuElemContainer menu;
    private LevelDisplay levelDisplay;

    public SnakeRun(Context c, SnakeView s, ProgressBar e, MenuElemContainer m, long u, LevelDisplay ld){
        this.c=c;
        this.snakeView=s;
        this.updateDelay=u;
        this.ehseg=e;
        this.menu=m;
        this.levelDisplay=ld;
        kezelo=new Handler();
    }

    //Eljndítunk egy szálat
    public void fuss(){
        kezelo.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Amennyiben még folyik a játék akkor uodatelunk rajta és rekurzívan ujrahívjuk
                if(!snakeView.didLost()) {
                    snakeView.update();
                    kezelo.postDelayed(this, updateDelay);
                }
                //Amennyiben a játékos vesztett akkor a megfelelő mutatót frissítjük, majd kiirunk egy üzenetet a megszerzett pontokkal
                // s megjelenítsük a menüt
                else{
                    if(ehseg.getProgress()<100)
                        ehseg.setProgress(ehseg.getProgress()+snakeView.getPont());
                  if(ehseg.getProgress()<=0){
                        ehseg.setProgress(99);
                        levelDisplay.incLevel();
                        levelDisplay.updateLevel();
                    }
                    final Toast toast = Toast.makeText(c, "Vesztettél! Pontjaid: "+snakeView.getPont(), Toast.LENGTH_LONG);
                    toast.show();
                    menu.show();
                }
            }
        },updateDelay);
    }
}
