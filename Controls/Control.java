package com.example.kecseti.kislenyprojekt.Controls;

import android.view.MotionEvent;
import android.view.View;

import com.example.kecseti.kislenyprojekt.Drop.DropView;
import com.example.kecseti.kislenyprojekt.Flappy.FlappyView;
import com.example.kecseti.kislenyprojekt.Snake.SnakeView;


//Ez az osztály minden játék irányításásért felel
public class Control implements View.OnTouchListener {

    private SnakeView snakeView;
    private DropView dropView;
    private FlappyView flappyView;
    private float eloX,eloY;


    public Control(SnakeView sw,DropView d,FlappyView f){
        this.snakeView=sw;
        this.dropView=d;
        this.flappyView=f;
    }

    private void SnakeTouch(MotionEvent event){
        switch(event.getAction()){
            //A snake játék irányítása érdekes, amerre menni akrunk arra kell az ujjunk húzni a képernyőn majd azt felemelni
            case MotionEvent.ACTION_DOWN:
                //Amikor rárakjuk a képernyőre az ujjunk lementjuk a poziciókat
                eloX=event.getX();
                eloY=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                //amikor felemltük akkor szintén lemetjük a két poziciót
                float ujX=event.getX();
                float ujY=event.getY();

                //Kiszámoljuk hogy függőlegesen vagy vizszintesen volt-e a csusztatás
                if(Math.abs(ujX-eloX)>Math.abs(ujY-eloY)){
                    //Ha vizsszintesen asszerint állítjuk be az irányt hogy merrefele
                    if(ujX>eloX){
                        snakeView.updateDir(1);
                    }else{
                        snakeView.updateDir(2);
                    }
                }else{
                    //Függőleges esetben ugyanígy
                    if(ujY>eloY){
                        snakeView.updateDir(4);
                    }else{
                        snakeView.updateDir(3);
                    }
                }
                break;
        }
    }

    private void FlappyTouch(MotionEvent event){
        //A flappy játékot úgy irányíthatjuk hogy egyszerűen hozzáérünk a képernyhöz ha felfele akarunk haladni
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                flappyView.updateDir(true);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    private void DropTouch(MotionEvent event){
        //A drop játékot úgy idányíthatjuk hogy  képernyőt jobbról megérintve jobbra, balról megérintve balra indul el a játékos
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(event.getX()<dropView.getWidth()/2) dropView.updateDir(-1);
                else dropView.updateDir(1);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Annak függvényében hogy éppen melyik annak az érintését állítjuk be
        if(!snakeView.didLost()) SnakeTouch(event);
        if(!dropView.didLost() ) DropTouch(event );
        if(!flappyView.didLost())FlappyTouch(event);
        return true;
    }
}
