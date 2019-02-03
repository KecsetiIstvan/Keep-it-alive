package com.example.kecseti.kislenyprojekt.Flappy;

import android.media.MediaPlayer;
import android.view.View;

import com.example.kecseti.kislenyprojekt.R;
import com.example.kecseti.kislenyprojekt.Tiles.BasicTile;
import com.example.kecseti.kislenyprojekt.Tiles.PipeTile;

import java.util.Random;

public class FlappyEngine {
   private Random rand;
   private Boolean run;
   private  int pontok;
   private Boolean lost;
   private Boolean fel;
   private BasicTile player;
   private PipeTile pipe;
   private PipeTile pipe2;
   private int randY;
   private int felfele;
   private View ablak;
   private MediaPlayer beep;
   private MediaPlayer death;
   private Boolean hang;

   public FlappyEngine(View v){
       rand=new Random();
       this.ablak=v;
       //Jelenleg nem futtatjuk a játékot és nem is vesztettünk MÉG, muhahahahaha
       this.run=false;
       this.lost=false;
       beep = MediaPlayer.create(ablak.getContext(),R.raw.beep);
       death = MediaPlayer.create(ablak.getContext(),R.raw.death);
   }
   //A játék inditásakor hívódik meg
   public void start(){
       //Mindig nulla ponttal kezdünk, sosem felfele, és még nem vesztettünk, mondom MÉG
       pontok = 0;
       fel=false;
       lost = false;

       //Beállítjuk a játékos kezdőpozicióját, illetve azt hogy most épp zuhanjon
       player=new BasicTile(100,70);
       player.setLocation(200,100);
       felfele=0;

       //Az első akadály beállítása illetve a rés amin a játékos át kell férjen
       pipe=new PipeTile(ablak.getHeight(),200);
       pipe.setLocation(ablak.getWidth(),0);
       pipe.setGapSize(350);
       pipe.setGapY(700);

       //A második hasonlóan
       pipe2=new PipeTile(ablak.getHeight(),200);
       pipe2.setLocation(ablak.getWidth()+ablak.getWidth()/2+pipe.getWidth()/2,0);
       pipe2.setGapSize(350);
       pipe2.setGapY(1000);

   }

   private Boolean collision(){

       //Ha nekimentünk valamelyik akadálynak, vagy a tetjén és az alján a képernyőnek
       if(player.getX()+player.getWidth()>=pipe.getX() && player.getX()+player.getWidth()<=pipe.getX()+pipe.getWidth() && player.getY()<=pipe.getGapY())return true;
       if(player.getX()+player.getWidth()>=pipe.getX() && player.getX()+player.getWidth()<=pipe.getX()+pipe.getWidth() && player.getY()+player.getHeight()>=pipe.getGapY()+pipe.getGapSize()) return true;
       if(player.getX()+player.getWidth()>=pipe2.getX() && player.getX()+player.getWidth()<=pipe2.getX()+pipe2.getWidth() && player.getY()<=pipe2.getGapY())return true;
       if(player.getX()+player.getWidth()>=pipe2.getX() && player.getX()+player.getWidth()<=pipe2.getX()+pipe2.getWidth() && player.getY()+player.getHeight()>=pipe2.getGapY()+pipe2.getGapSize())return true;
       if(player.getX()>=pipe.getX() && player.getX()<=pipe.getWidth()+pipe.getX() && player.getY()+player.getHeight()>=pipe.getGapY()+pipe.getGapSize()) return true;
       if(player.getX()>=pipe2.getX() && player.getX()<=pipe2.getWidth()+pipe2.getX() && player.getY()+player.getHeight()>=pipe2.getGapY()+pipe2.getGapSize()) return true;
       if(player.getY()<=0)                                   return true;
       if(player.getY()+player.getHeight()>=ablak.getHeight())return true;

       //Ha nem volt igaz visszatérés
       return false;

   }

   public void update(){
       //Most fut a játék
       run=true;

       //Ha tudunk felfele menni megyünk
       if(fel && felfele <=10) {
           player.setLocation(player.getX(),player.getY()-15);
           felfele++;
       }
       //Ha nem akkor zuhanunk
       else {
           felfele=0;
           fel=false;
           player.setLocation(player.getX(),player.getY()+10);
       }

       //Közelítjük az első akadályt, ha kimentünk a képernyőből akkor jobbról rakjuk és ujra jön, olyan hatást keltve mintha folyton más jönne,
       //ilyenkor úgy magasságot randomolunk
       pipe.setLocation(pipe.getX()-10,pipe.getY());
       if(pipe.getX()+pipe.getWidth()<=0){
           pipe.setLocation(ablak.getWidth(),0);
           randY=rand.nextInt(1000)+pipe.getGapSize();
           pipe.setGapY(randY);
       }
       //ugyanaz a második akadályra is
       pipe2.setLocation(pipe2.getX()-10,pipe2.getY());
       if(pipe2.getX()+pipe2.getWidth()<=0){
           pipe2.setLocation(ablak.getWidth(),0);
           randY=rand.nextInt(1000)+pipe2.getGapSize();
           pipe2.setGapY(randY);
       }

       //Hogyha épp elhagytuk valamelyik akadályt akkor növeljük a pontokat
       if( pipe.getX()+pipe.getWidth() ==player.getX()){ pontok++; if(hang)beep.start();}
       if(pipe2.getX()+pipe2.getWidth()==player.getX()){ pontok++; if(hang)beep.start();}

       //Ha beleütköztünk valamelyik akadályba, vagy a képernyő tetejébe vagy aljába akkor meghaltunk
       //Akkor megáll a játék és vesztettünk
       if(collision()){
           run=false;
           lost=true;
           if(hang)death.start();
       }
   }

   //Lekérdezése annak hogy épp fut vagy vesztettünk, mennyi pontunk van, mi a játékos, mik az akadályok illetve az input beállítása
   public Boolean   getRun()           {return this.run;   }
   public Boolean   getLost()          {return this.lost;  }
   public int       getPont()          {return this.pontok;}
   public BasicTile getPlayer()        {return this.player;}
   public PipeTile  getPipe1()         {return this.pipe;  }
   public PipeTile  getPipe2()         {return this.pipe2; }
   public void      setFel(Boolean fel){this.fel=fel;     }
   public void      exit()             {this.lost=true;pontok=0;this.run=false;}
   public void setEffectSound(Boolean isNoisy){hang=isNoisy;}
}
