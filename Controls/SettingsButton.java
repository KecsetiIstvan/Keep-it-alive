package com.example.kecseti.kislenyprojekt.Controls;

import android.view.View;

public class SettingsButton implements  View.OnClickListener {

    private MenuElemContainer menu;
    public SettingsButton(MenuElemContainer m){
        this.menu=m;
    }

    @Override
    public void onClick(View v) {
        menu.hide();
        menu.settingsShow();
    }
}
