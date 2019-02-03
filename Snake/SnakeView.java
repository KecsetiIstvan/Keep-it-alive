package com.example.kecseti.kislenyprojekt.Snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.kecseti.kislenyprojekt.R;
import com.example.kecseti.kislenyprojekt.Tiles.BasicTile;

import java.util.Random;

public class SnakeView extends View {

    private Paint p;
    private int r,g,b;
    private int rv,gv,bv;
    private Random rand;
    private SnakeEngine motor;

    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p=new Paint();
        rand=new Random();
        motor=new SnakeEngine(this);
    }

    //Unity game engine-hez hasonlóan bontottam szét
    //A kezd az a start metódus, egysezr hívódik meg mikor a játék elindul
    public void kezd() {

        //Random startoló háttérszín
        r = rand.nextInt(255);
        g = rand.nextInt(255);
        b = rand.nextInt(255);
        // a háttérszín Változtatás értékek beállítása
        rv = 1;
        gv = -2;
        bv = -1;

        motor.start();

    }

    //Upeadte funkció, egyszer hívódik meg minden ciklusfordulóban
    public void update(){
        //Háttérszín változtatása folyamatosan
        r+=rv;
        g+=gv;
        b+=bv;
        if(r>250 || r<30) rv*=-1;
        if(g>250 || g<30) gv*=-1;
        if(b>250 || b<30) bv*=-1;

        motor.update();

        this.invalidate();
    }
    //Megjelenítés upedat-ja
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        if(motor.getRun()) {
            super.onDraw(canvas);
            //Kirajzoljuk a változó hátteret
            canvas.drawColor(Color.rgb(r, g, b));

            //Kirajzoljuk a kígyót
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(10f);
            p.setColor(Color.argb(0.05f,0,0,0));
            p.setTextSize(500);
            String text;
            if(motor.getPont()<=9) text="0" +motor.getPont();
            else                   text="" + motor.getPont();
            canvas.drawText(text, this.getWidth()/2-250, this.getHeight()/2, p);
            p.setStrokeWidth(5f);
            p.setColor(Color.BLACK);
            canvas.drawCircle(motor.getFej().getX(), motor.getFej().getY(), motor.getFej().getHeight(), p);
            p.setColor(Color.BLACK);
            BasicTile [] snakey=motor.getSnake();
            for (int i = 0; i < motor.getAktMeret(); i++)
                canvas.drawCircle(snakey[i].getX(), snakey[i].getY(), snakey[i].getHeight(), p);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.RED);
            canvas.drawCircle(motor.getFood().getX(), motor.getFood().getY(), motor.getFood().getHeight(), p);

        }else{
            canvas.isOpaque();
        }
    }


    //Lekérdezés és beállítása annak hogy épp folyik-e a játék
    //Ha vesztettünk annak lekérdezése és a pontoké is ebben az esetben
    //Irány upedat-je
    public Boolean didLost()                      {return motor.getLost(); }
    public int     getPont()                      {return motor.getPont();}
    public void    exit()                         {motor.exit();this.invalidate(); }
    public void    setEffectSound(Boolean isNoisy){motor.setEffectSound(isNoisy);}
    public void    updateDir(int irany)           {motor.updateDir(irany); }

}
