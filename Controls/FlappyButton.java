package com.example.kecseti.kislenyprojekt.Controls;

import android.view.View;

import com.example.kecseti.kislenyprojekt.Flappy.FlappyView;
import com.example.kecseti.kislenyprojekt.Runable.FlappyRun;

public class FlappyButton implements  View.OnClickListener {

    private FlappyView flappyView;
    private FlappyRun flappyRun;
    private  MenuElemContainer menu;

    public FlappyButton(FlappyView v, FlappyRun r,MenuElemContainer m) {
        this.flappyView = v;
        this.flappyRun = r;
        this.menu=m;
    }
    //Elrejtük az összes menüelemet, elindítjüka a játékot, majd a szálat
    @Override
    public void onClick(View v) {
        menu.hide();
        flappyView.kezd();
        flappyRun.fuss();
    }
}
