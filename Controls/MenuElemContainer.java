package com.example.kecseti.kislenyprojekt.Controls;

import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

//Az osztály azt a célt szolgálja hogy minden menüelemet egyszerre tudjun elrejteni illetve megjeleníteni
public class MenuElemContainer {
    private ImageButton snakeButton;
    private ImageButton dropButton;
    private ImageButton flappyButton;
    private ImageButton exitButton;
    private ProgressBar moral;
    private ProgressBar ehseg;
    private ProgressBar szomj;
    private TextView hungerText;
    private TextView thText;
    private TextView moralText;
    private WebView webView;
    private ImageButton settings;
    private ImageButton change;
    private TextView nev;

    TextView settingsLabel;
    TextView soundSettings;
    TextView effectSettings;
    Switch soundSwitch;
    Switch effectSwitch;

    TextView coustom;
    ImageButton elsoKari;
    ImageButton masodikKari;
    ImageButton harmadikKari;
    TextInputEditText playerNev;

    TextView level;
    TextView levelXp;

    public MenuElemContainer(ImageButton b,ImageButton d,ImageButton f, ProgressBar m, ProgressBar e,ProgressBar sz,
                             ImageButton ex,WebView web,ImageButton sett,ImageButton ch,TextView nev,TextView h,TextView t,
                             TextView mo, TextView selabel,TextView soundlabel,TextView efectlabel,Switch soundS,Switch effectS,
                             TextView coustom,ImageButton elso,ImageButton masodik,ImageButton harmadik, TextInputEditText playerNev,
                             TextView level,TextView levelXp) {
        this.snakeButton = b;
        this.dropButton =d;
        this.flappyButton=f;
        this.moral=m;
        this.ehseg=e;
        this.szomj=sz;
        this.hungerText=h;
        this.thText=t;
        this.moralText=mo;
        this.exitButton=ex;
        this.webView=web;
        this.nev=nev;
        this.settings=sett;
        this.change=ch;
        this.settingsLabel=selabel;
        this.soundSettings=soundlabel;
        this.effectSettings=efectlabel;
        this.soundSwitch=soundS;
        this.effectSwitch=effectS;
        this.coustom=coustom;
        this.elsoKari=elso;
        this.masodikKari=masodik;
        this.harmadikKari=harmadik;
        this.playerNev=playerNev;
        this.level=level;
        this.levelXp=levelXp;
    }

    public void settingsHide(){
        settingsLabel.setVisibility(View.INVISIBLE);
        soundSettings.setVisibility(View.INVISIBLE);
        effectSettings.setVisibility(View.INVISIBLE);
        soundSwitch.setVisibility(View.INVISIBLE);
        effectSwitch.setVisibility(View.INVISIBLE);
    }

    public void settingsShow(){
        settingsLabel.setVisibility(View.VISIBLE);
        soundSettings.setVisibility(View.VISIBLE);
        effectSettings.setVisibility(View.VISIBLE);
        soundSwitch.setVisibility(View.VISIBLE);
        effectSwitch.setVisibility(View.VISIBLE);
    }

    public void show(){
        exitButton.setVisibility(View.INVISIBLE);
        snakeButton.setVisibility(View.VISIBLE);
        dropButton.setVisibility(View.VISIBLE);
        flappyButton.setVisibility(View.VISIBLE);
        moral.setVisibility(View.VISIBLE);
        ehseg.setVisibility(View.VISIBLE);
        szomj.setVisibility(View.VISIBLE);
        hungerText.setVisibility(View.VISIBLE);
        thText.setVisibility(View.VISIBLE);
        moralText.setVisibility(View.VISIBLE);
        webView.setVisibility(View.VISIBLE);
        change.setVisibility(View.VISIBLE);
        settings.setVisibility(View.VISIBLE);
        nev.setVisibility(View.VISIBLE);
        level.setVisibility(View.VISIBLE);
        levelXp.setVisibility(View.VISIBLE);

    }
    public void hide(){
        exitButton.setVisibility(View.VISIBLE);
        snakeButton.setVisibility(View.INVISIBLE);
        dropButton.setVisibility(View.INVISIBLE);
        flappyButton.setVisibility(View.INVISIBLE);
        moral.setVisibility(View.INVISIBLE);
        ehseg.setVisibility(View.INVISIBLE);
        szomj.setVisibility(View.INVISIBLE);
        hungerText.setVisibility(View.INVISIBLE);
        thText.setVisibility(View.INVISIBLE);
        moralText.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.INVISIBLE);
        change.setVisibility(View.INVISIBLE);
        settings.setVisibility(View.INVISIBLE);
        nev.setVisibility(View.INVISIBLE);
        level.setVisibility(View.INVISIBLE);
        levelXp.setVisibility(View.INVISIBLE);
    }
    public void coustomShow(){
        coustom.setVisibility(View.VISIBLE);
        elsoKari.setVisibility(View.VISIBLE);
        masodikKari.setVisibility(View.VISIBLE);
        harmadikKari.setVisibility(View.VISIBLE);
        webView.setVisibility(View.VISIBLE);
        playerNev.setVisibility(View.VISIBLE);
    }
    public void coustomHide(){
        coustom.setVisibility(View.INVISIBLE);
        elsoKari.setVisibility(View.INVISIBLE);
        masodikKari.setVisibility(View.INVISIBLE);
        harmadikKari.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.VISIBLE);
        playerNev.setVisibility(View.INVISIBLE);

    }

}
