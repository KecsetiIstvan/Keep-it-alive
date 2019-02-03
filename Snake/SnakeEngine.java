package com.example.kecseti.kislenyprojekt.Snake;

import android.media.MediaPlayer;
import android.view.View;

import com.example.kecseti.kislenyprojekt.R;
import com.example.kecseti.kislenyprojekt.Tiles.BasicTile;

import java.util.Random;

public class SnakeEngine {

    private int x,y;
    private Boolean fel, le, jobbra, balra;
    private BasicTile[] snake;
    private BasicTile fej;
    private BasicTile food;
    private int randX,randY;
    private int aktMeret;
    private int pontok;
    private Boolean lost;
    private Boolean run;
    private MediaPlayer beep;
    private MediaPlayer death;
    private Boolean hang;
    private View ablak;
    private Random rand;

    public SnakeEngine(View v){
        this.ablak=v;
        run=false;
        beep = MediaPlayer.create(v.getContext(),R.raw.beep);
        death = MediaPlayer.create(v.getContext(),R.raw.death);
        rand=new Random();
    }

    public void start(){
        pontok=0;
        lost=false;

        food=new BasicTile(15,15);
        randX=500;
        randY=500;
        food.setLocation(randX,randY);

        //Kezdp kontrol, mindig jobbra kezdünk
        fel=false;
        le=false;
        jobbra=false;
        balra=true;

        //Kígyó inicializása, alapértelmezetten 5 bogyó hosszúra
        aktMeret=5;
        snake=new BasicTile[100];
        fej=new BasicTile(28,28);
        for(int i=0;i<snake.length;i++)
            snake[i]=new BasicTile(25,25);

        //A fej poziciója
        x=500;
        y=600;
        fej.setLocation(x,y);

        //Minden más a fejhez viszonyított pozicióban
        for(int i=0;i<aktMeret;i++) {
            if(i==0) {
                snake[i].setLocation(fej.getX(), fej.getY());
            }else {
                snake[i].setLocation(snake[i - 1].getX() + 2 * snake[i].getHeight(), snake[i - 1].getY());
            }
        }

    }

    private Boolean collison(){
        for(int i=0;i<=aktMeret;i++)
            if(snake[i].getX()==x && snake[i].getY()==y) return true;

        return false;
    }

    public void update(){
        run=true;
        //A kígyót alkotó bogyók upedateje, mindig az előtte levő szerint
        for(int i=aktMeret-1;i>=0;i--) {
            if(i==0)
                snake[i].setLocation(fej.getX(),fej.getY());
            else
                snake[i].setLocation(snake[i-1].getX(),snake[i-1].getY());
        }

        //Végül a fej
        fej.setLocation(x,y);

        //Az irányítás szerint beállítandó a menetirány
        if(jobbra) x+=2*fej.getHeight()+1;
        if(balra)  x-=2*fej.getHeight()+1;
        if(fel)    y-=2*fej.getHeight()+1;
        if(le)	   y+=2*fej.getHeight()+1;

        int hibaHatar=18;

        if((randX+2*food.getHeight()<=x+2*fej.getHeight()+hibaHatar && randX >=x-hibaHatar)&& (randY+2*food.getHeight()<=y+2*fej.getHeight()+hibaHatar && randY >=y-hibaHatar)){
            if(hang)beep.start();
            randX=rand.nextInt(ablak.getWidth()-120)+60;
            randY=rand.nextInt(ablak.getHeight()-120)+60;
            while(randX%2*fej.getHeight()!=0)randX--;
            while(randY%2*fej.getHeight()!=0)randY--;
            food.setLocation(randX,randY);
            snake[aktMeret]=new BasicTile(25,25);
            aktMeret++;
            pontok++;

        }

        //Ha kimenne a képernyőből akkor a másikoldalt jön vissza
        if(x<=0-fej.getHeight()) x=ablak.getWidth()-fej.getWidth();
        if(y<=0-fej.getHeight()) y=ablak.getHeight()-fej.getWidth();
        if(y>=ablak.getHeight())y=-fej.getHeight();
        if(x>=ablak.getWidth()) x=-fej.getHeight();

        if(collison()) {
            lost=true;
            run=false;
            if(hang)death.start();
        }
    }

    public void updateDir(int irany){
        switch(irany){
            case 1: //jobbra
                if(!balra){
                    jobbra=true;
                    balra=false;
                    fel=false;
                    le=false;
                }
                break;
            case 2: //balra
                if(!jobbra){
                    jobbra=false;
                    balra=true;
                    fel=false;
                    le=false;
                }
                break;
            case 3: //fel
                if(!le){
                    jobbra=false;
                    balra=false;
                    fel=true;
                    le=false;
                }
                break;
            case 4: //le
                if(!fel){
                    jobbra=false;
                    balra=false;
                    fel=false;
                    le=true;
                }
                break;
        }
    }

    public void        exit()                         {pontok=0;lost=true;run=false;}
    public int         getPont()                      {return this.pontok;}
    public Boolean     getLost()                      {return this.getLost();}
    public void        setEffectSound(Boolean isNoisy){hang=isNoisy;}
    public Boolean     getRun()                       {return this.run;}
    public BasicTile   getFej()                       {return this.fej;}
    public BasicTile   getFood()                      {return this.food;}
    public BasicTile[] getSnake()                     {return this.snake;}
    public int         getAktMeret()                  {return this.aktMeret;}
}
