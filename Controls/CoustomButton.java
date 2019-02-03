package com.example.kecseti.kislenyprojekt.Controls;

import android.view.View;

public class CoustomButton implements  View.OnClickListener {

    private MenuElemContainer menu;
    public CoustomButton(MenuElemContainer m){
        this.menu=m;
    }

    @Override
    public void onClick(View v) {
        menu.hide();
        menu.coustomShow();
    }
}
