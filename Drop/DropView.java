package com.example.kecseti.kislenyprojekt.Drop;

import android.content.Context;
import android.content.SharedPreferences;
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

public class DropView extends View {

    private Paint p;
    private int r,g,b;
    private int rv,gv,bv;

    private Random rand;
    private DropEngine motor;

    public DropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p=new Paint();
        rand=new Random();
        motor=new DropEngine(this);
    }

    public void kezd() {
        //Random startoló háttérszín
        r = rand.nextInt(255);
        g = rand.nextInt(255);
        b = rand.nextInt(255);

        rv = 1;
        gv = -1;
        bv = -1;

    }

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        if(motor.getRun()) {
            super.onDraw(canvas);
            canvas.drawColor(Color.rgb(r, g, b));

            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(5f);
            p.setColor(Color.BLACK);
            canvas.drawRect(motor.getPlayer().getX(), motor.getPlayer().getY(),motor.getPlayer().getX()+motor.getPlayer().getWidth(), motor.getPlayer().getY()+motor.getPlayer().getHeight(), p);
            BasicTile[] potyi=motor.getLeeso();
            for(int i=0;i<potyi.length;i++)
                canvas.drawRect(potyi[i].getX(),potyi[i].getY(),potyi[i].getX()+potyi[i].getWidth(),potyi[i].getY()+potyi[i].getHeight(),p);

            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(10f);
            p.setColor(Color.argb(0.05f,0,0,0));
            p.setTextSize(500);
            String text;
            if(motor.getPont()<=9) text="0" +motor.getPont();
            else                   text="" + motor.getPont();
            canvas.drawText(text, this.getWidth()/2-250, this.getHeight()/2, p);

        }else{
            canvas.isOpaque();
        }
    }

    //Lekérdezés és beállítása annak hogy épp folyik-e a játék
    public void    updateDir(int irany)           {motor.updateDir(irany);}
    public Boolean didLost()                      {return motor.getLost(); }
    public int     getPont()                      {return motor.getPont();}
    public void    exit()                         {motor.exit(); this.invalidate();}
    public void    setEffectSound(Boolean isNoisy){motor.soundEffectSound(isNoisy);}
}
