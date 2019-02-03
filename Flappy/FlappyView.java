package com.example.kecseti.kislenyprojekt.Flappy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class FlappyView extends View {

    private Paint p;
    private int r,g,b;
    private int rv,gv,bv;
    private Canvas canvas;

    private Random rand;
    private FlappyEngine motor;

    public FlappyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p=new Paint();
        rand=new Random();
        motor=new FlappyEngine(this);
    }

    public void kezd() {
        //Random startoló háttérszín
        r = rand.nextInt(255);
        g = rand.nextInt(255);
        b = rand.nextInt(255);

        //Ezek alapjá fog a háttér random mód változni
        rv = 1;
        gv = -1;
        bv = 1;

        //A motor elinditása
        motor.start();
    }

    public void update(){
        //Háttérszín változtatása folyamatosan
        r+=rv;
        g+=gv;
        b+=bv;
        //Ha kiugrottunk a színskálából akkor változtatjuk az irányt
        if(r>250 || r<30) rv*=-1;
        if(g>250 || g<30) gv*=-1;
        if(b>250 || b<30) bv*=-1;

        //a motor upedat-ja
        motor.update();

        //Ujrarajzolás
        this.invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        if(motor.getRun()) {
            this.canvas=canvas;
            super.onDraw(canvas);
            //A változó háttérszín rajzolása
            canvas.drawColor(Color.rgb(r, g, b));

            //Csak körvonalak legyenek kirajzolva, szinek, körvonalvastagsag beállítása
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(7f);
            p.setColor(Color.BLACK);

            //Player kirajzolása
            canvas.drawRoundRect(motor.getPlayer().getX(),motor.getPlayer().getY(),motor.getPlayer().getX()+motor.getPlayer().getWidth(),motor.getPlayer().getY()+motor.getPlayer().getHeight(),30,30,p);

            //Első akadály kirajzolása
            canvas.drawRect(motor.getPipe1().getX(),motor.getPipe1().getY(),motor.getPipe1().getX()+motor.getPipe1().getWidth(),motor.getPipe1().getGapY(),p);
            canvas.drawRect(motor.getPipe1().getX(),motor.getPipe1().getY()+motor.getPipe1().getGapY()+motor.getPipe1().getGapSize(),motor.getPipe1().getX()+motor.getPipe1().getWidth(),motor.getPipe1().getY()+motor.getPipe1().getGapY()+motor.getPipe1().getGapSize()+motor.getPipe1().getHeight(),p);

            //Második akadály kirajzolása
            canvas.drawRect(motor.getPipe2().getX(),motor.getPipe2().getY(),motor.getPipe2().getX()+motor.getPipe2().getWidth(),motor.getPipe2().getGapY(),p);
            canvas.drawRect(motor.getPipe2().getX(),motor.getPipe2().getY()+motor.getPipe2().getGapY()+motor.getPipe2().getGapSize(),motor.getPipe2().getX()+motor.getPipe2().getWidth(),motor.getPipe2().getY()+motor.getPipe2().getGapY()+motor.getPipe2().getGapSize()+motor.getPipe2().getHeight(),p);

            //Szöveg megjelenítése, A keret illetve a szín, szövegméret beállítása
            p.setStrokeWidth(10f);
            p.setColor(Color.argb(0.05f,0,0,0));
            p.setTextSize(500);
            String text;
            if(motor.getPont()<=9) text="0" +motor.getPont();
            else                   text="" + motor.getPont();
            canvas.drawText(text, this.getWidth()/2-250, this.getHeight()/2, p);

        }else{
            //Ha nem megy a játék akkor elrejtjük az adott alapot
            canvas.isOpaque();
        }
    }

    //Annak lekérdezése hogy a játék még folyik-e, illetve a pontok lekérdezése, valamint az irany beállítása
    public Boolean didLost()             {return motor.getLost(); }
    public void    exit()                {motor.exit();    this.invalidate(); }
    public int     getPont()             {return motor.getPont(); }
    public void    updateDir(Boolean fel){motor.setFel(fel);      }
    public void setEffectSound(Boolean isNoisy){motor.setEffectSound(isNoisy);}


}
