package com.example.kecseti.kislenyprojekt.Controls;

import android.view.View;

import com.example.kecseti.kislenyprojekt.Runable.SnakeRun;
import com.example.kecseti.kislenyprojekt.Snake.SnakeView;

public class SnakeButton implements  View.OnClickListener{

    private SnakeView snakeView;
    private SnakeRun snakeRun;
    private MenuElemContainer menu;

    public SnakeButton(SnakeView v, SnakeRun r, MenuElemContainer m) {
        this.snakeView = v;
        this.snakeRun = r;
        this.menu=m;
    }
    //Elrejtük az összes menüelemet, elindítjüka a játékot, majd a szálat
    @Override
    public void onClick(View v) {
        menu.hide();
        snakeView.kezd();
        snakeRun.fuss();
    }
}
