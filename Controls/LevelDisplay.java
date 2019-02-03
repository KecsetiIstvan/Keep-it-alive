package com.example.kecseti.kislenyprojekt.Controls;

import android.content.SharedPreferences;
import android.widget.TextView;


public class LevelDisplay {
    private TextView levelXp;
    private int level;
    private SharedPreferences mentes;

    public LevelDisplay(TextView levelXp,int level,SharedPreferences mentes){
        this.levelXp=levelXp;
        this.level=level;
        this.mentes=mentes;
    }

    public void incLevel(){
        this.level++;
    }
    public void decLevel(){
        this.level--;
    }

    public void updateLevel(){
        SharedPreferences.Editor editor=mentes.edit();
        editor.putInt("szint",level);
        editor.commit();
        this.levelXp.setText(""+level);
    }

}
