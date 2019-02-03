package com.example.kecseti.kislenyprojekt.Controls;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.kecseti.kislenyprojekt.Drop.DropView;
import com.example.kecseti.kislenyprojekt.Runable.DropRun;

public class DropButton  implements  View.OnClickListener {

    private DropView dropView;
    private DropRun dropRun;
    private MenuElemContainer menu;

    public DropButton(DropView v,DropRun r,MenuElemContainer m) {
        this.dropView = v;
        this.dropRun = r;
        this.menu=m;
    }
    //Elrejtük az összes menüelemet, elindítjüka a játékot, majd a szálat
    @Override
    public void onClick(View v) {
        menu.hide();
        dropView.kezd();
        dropRun.fuss();
    }

}
