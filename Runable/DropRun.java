package com.example.kecseti.kislenyprojekt.Runable;

import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kecseti.kislenyprojekt.Controls.LevelDisplay;
import com.example.kecseti.kislenyprojekt.Controls.MenuElemContainer;
import com.example.kecseti.kislenyprojekt.Drop.DropView;

public class DropRun {


    private Handler kezelo;
    private long updateDelay;
    private Context c;
    private DropView dropView;
    private ProgressBar szomj;
    private MenuElemContainer menu;
    private LevelDisplay levelDisplay;

    public DropRun(Context c, DropView s, ProgressBar sz, MenuElemContainer m,long u,LevelDisplay ld){
        this.c=c;
        this.dropView=s;
        this.updateDelay=u;
        this.szomj=sz;
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
                if(!dropView.didLost()) {
                    dropView.update();
                    kezelo.postDelayed(this, updateDelay);
                }
                //Amennyiben a játékos vesztett akkor a megfelelő mutatót frissítjük, majd kiirunk egy üzenetet a megszerzett pontokkal
                // s megjelenítsük a menüt
                else{
                    if(szomj.getProgress()<100)
                        szomj.setProgress(szomj.getProgress()+dropView.getPont());
                    if(szomj.getProgress()<=0) {
                        szomj.setProgress(99);
                        levelDisplay.incLevel();
                        levelDisplay.updateLevel();
                    }
                    final Toast toast = Toast.makeText(c, "Vesztettél! Pontjaid: "+dropView.getPont(), Toast.LENGTH_LONG);
                    toast.show();
                    menu.show();
                }
            }
        },updateDelay);
    }

}
