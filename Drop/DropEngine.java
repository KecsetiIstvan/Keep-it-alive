package com.example.kecseti.kislenyprojekt.Drop;

import android.media.MediaPlayer;
import android.view.View;

import com.example.kecseti.kislenyprojekt.R;
import com.example.kecseti.kislenyprojekt.Tiles.BasicTile;

import java.util.Random;

public class DropEngine {

    private Boolean run;
    private  int pontok;
    private Boolean lost;
    private int irany;
    private BasicTile player;
    private BasicTile[] leeso;
    private int randX;
    private MediaPlayer beep;
    private MediaPlayer death;
    private Boolean hang;
    private Random rand;
    private View ablak;

    public DropEngine(View v){
        ablak=v;
        beep = MediaPlayer.create(v.getContext(),R.raw.beep);
        death = MediaPlayer.create(v.getContext(),R.raw.death);
        rand=new Random();
        this.run=false;
        this.lost=false;
    }

    public void start(){
        pontok = 0;
        irany = 1;
        lost = false;

        player = new BasicTile(200, 40);
        player.setLocation(0, 0);
        leeso = new BasicTile[5];
        for (int i = 0; i < leeso.length; i++){
            leeso[i] = new BasicTile(30, 30);
            randX = rand.nextInt(1000 - leeso[i].getWidth());
            leeso[i].setLocation(randX, 0-i*380);
        }

    }

    private Boolean collison(){

        for(int i=0;i<leeso.length;i++)
            if(leeso[i].getY()+leeso[i].getHeight()>=ablak.getHeight()) return true;

       return false;
    }

    public void update(){

        run=true;
        player.setLocation(player.getX()+20*irany,ablak.getHeight()-100);

        for(int i=0;i<leeso.length;i++)
            leeso[i].setLocation(leeso[i].getX(),leeso[i].getY()+15);

        for(int i=0;i<leeso.length;i++)
            if(leeso[i].getY()+leeso[i].getHeight()>=player.getY() && (leeso[i].getX()>=player.getX() && leeso[i].getX()+leeso[i].getWidth()<=player.getX()+player.getWidth()) ){
                if(hang) beep.start();
                randX=rand.nextInt(ablak.getWidth()-leeso[i].getWidth());
                leeso[i].setLocation(randX,0);
                pontok++;
            }

        if(player.getX()<=0) player.setLocation(0,player.getY());
        if(player.getX()+player.getWidth()>=ablak.getWidth())player.setLocation(ablak.getWidth()-player.getWidth(),player.getY());

        if(collison()){
            lost=true;
            run=false;
            if(hang)death.start();
        }
    }

    public void        updateDir(int irany)             {this.irany=irany;}
    public void        exit()                           {this.lost=true;pontok=0;this.run=false;}
    public void        soundEffectSound(Boolean isNoisy){this.hang=isNoisy;}
    public int         getPont()                        {return this.pontok;}
    public Boolean     getLost()                        {return  this.lost;}
    public Boolean     getRun()                         {return this.run;}
    public BasicTile   getPlayer()                      {return this.player;}
    public BasicTile[] getLeeso()                       {return  this.leeso;}
}
