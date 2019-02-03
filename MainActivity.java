package com.example.kecseti.kislenyprojekt;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.kecseti.kislenyprojekt.Controls.CoustomButton;
import com.example.kecseti.kislenyprojekt.Controls.DropButton;
import com.example.kecseti.kislenyprojekt.Controls.ExitButton;
import com.example.kecseti.kislenyprojekt.Controls.FlappyButton;
import com.example.kecseti.kislenyprojekt.Controls.LevelDisplay;
import com.example.kecseti.kislenyprojekt.Controls.MenuElemContainer;
import com.example.kecseti.kislenyprojekt.Controls.SettingsButton;
import com.example.kecseti.kislenyprojekt.Controls.SnakeButton;
import com.example.kecseti.kislenyprojekt.Controls.Control;
import com.example.kecseti.kislenyprojekt.Drop.DropView;
import com.example.kecseti.kislenyprojekt.Flappy.FlappyView;
import com.example.kecseti.kislenyprojekt.Runable.DropRun;
import com.example.kecseti.kislenyprojekt.Runable.FlappyRun;
import com.example.kecseti.kislenyprojekt.Runable.ProgressRun;
import com.example.kecseti.kislenyprojekt.Runable.SnakeRun;
import com.example.kecseti.kislenyprojekt.Snake.SnakeView;

public class MainActivity extends AppCompatActivity{
    MediaPlayer hatter;
    SharedPreferences mentes;

    Boolean soundy;
    Boolean effecty;

    Boolean changeSound;
    Boolean changeEffect;
    Boolean changeKarakter;
    Boolean changeName;

    int karakter;

    String player;

    private void save(){
        SharedPreferences.Editor editor=mentes.edit();
        if(changeEffect){
            editor.remove("effect");
            editor.putBoolean("effect",effecty);}
        if(changeSound){
            editor.remove("sound");
            editor.putBoolean("sound", soundy );}
        if(changeKarakter){
            editor.remove("karakter");
            editor.putInt("karakter",karakter);}
        if(changeName){
            editor.remove("playerNev");
            editor.putString("playerNev",player);}
        editor.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        TextView h=findViewById(R.id.textView5);
        TextView t=findViewById(R.id.textView6);
        TextView m=findViewById(R.id.textView7);
        TextView coustom=findViewById(R.id.change);
        TextView settingsLabel=findViewById(R.id.settings);
        TextView soundSettings=findViewById(R.id.sound);
        TextView effectSettings=findViewById(R.id.effect);
        TextView level=findViewById(R.id.level);
        TextView levelXp=findViewById(R.id.levelSzint);
        ImageButton elsoKari=findViewById(R.id.egyesKari);
        ImageButton masodikKari=findViewById(R.id.kettesKari);
        ImageButton harmadikKari=findViewById(R.id.harmasKari);
        ImageButton exit=findViewById(R.id.exitButton);
        ImageButton settings=findViewById(R.id.settingsButton);
        ImageButton change=findViewById(R.id.changeButton);
        ImageButton flappyButton=findViewById(R.id.flappyButton);
        ImageButton snakeButton=findViewById(R.id.snakeGomb);
        ImageButton dropButton=findViewById(R.id.dropButton);
        Switch soundSwitch=findViewById(R.id.soundSwitch);
        Switch effectSwitch=findViewById(R.id.effectSwitch);
        ProgressBar ehseg=findViewById(R.id.Ehseg);
        ProgressBar moral=findViewById(R.id.Moral);
        ProgressBar szomj=findViewById(R.id.Szomjusag);
        final TextView nev=findViewById(R.id.nevText);
        final TextInputEditText playerNev=findViewById(R.id.playerNev);
        final WebView view = findViewById(R.id.giffplay);
        final DropView dropView=findViewById(R.id.dropView);
        final FlappyView flappyView=findViewById(R.id.flappyView);
        final SnakeView snakeView = findViewById(R.id.snakeView);

        SnakeRun snakeRun;
        DropRun dropRun;
        FlappyRun flappyRun;

        MenuElemContainer menu=new MenuElemContainer(snakeButton,dropButton,flappyButton,ehseg,szomj,moral,
                                                     exit,view,settings,change,nev,h,t,m, settingsLabel,
                                                     soundSettings,effectSettings,soundSwitch,effectSwitch,
                                                     coustom,elsoKari,masodikKari,harmadikKari,playerNev,level,levelXp);

        menu.settingsHide();
        menu.coustomHide();

        hatter= MediaPlayer.create(this,R.raw.hatter);
        hatter.setLooping(true);

        view.setBackgroundColor(Color.TRANSPARENT);
        view.setVerticalScrollBarEnabled(false);
        view.setHorizontalScrollBarEnabled(false);

        changeSound=false;
        changeEffect=false;
        changeName=false;
        changeKarakter=false;

        mentes=this.getSharedPreferences("jatek",Context.MODE_PRIVATE);
        soundSwitch.setChecked(mentes.getBoolean("sound",true));
        effectSwitch.setChecked(mentes.getBoolean("effect",true));
        if(mentes.getBoolean("sound",true))hatter.start();
        else hatter.pause();
        dropView.setEffectSound(mentes.getBoolean("effect",true));
        snakeView.setEffectSound(mentes.getBoolean("effect",true));
        flappyView.setEffectSound(mentes.getBoolean("effect",true));
        LevelDisplay levelDisplay=new LevelDisplay(levelXp,mentes.getInt("szint",0),mentes);
        levelDisplay.updateLevel();
        final int mutato=mentes.getInt("karakter",1);
        switch(mutato){
            case 1:view.loadUrl("file:///android_asset/1.gif");
                break;
            case 2:view.loadUrl("file:///android_asset/2.gif");
                break;
            case 3: view.loadUrl("file:///android_asset/3.gif");
                break;
            default:view.loadUrl("file:///android_asset/1.gif");
        }

        Control control=new Control(snakeView,dropView,flappyView);

        snakeView.setOnTouchListener(control);
        flappyView.setOnTouchListener(control);
        dropView.setOnTouchListener(control);

        snakeRun=new SnakeRun(this,snakeView,ehseg,menu,90,levelDisplay);
        flappyRun=new FlappyRun(this,flappyView,moral, menu,30,levelDisplay);
        dropRun=new DropRun(this,dropView,szomj,menu,30,levelDisplay);

        dropButton.setOnClickListener(new DropButton(dropView,dropRun,menu));
        flappyButton.setOnClickListener(new FlappyButton(flappyView,flappyRun,menu));
        snakeButton.setOnClickListener(new SnakeButton(snakeView,snakeRun,menu));

        settings.setOnClickListener(new SettingsButton(menu));
        change.setOnClickListener  (new CoustomButton(menu));
        exit.setOnClickListener    (new ExitButton(snakeView,flappyView,dropView,menu));
        exit.setVisibility(View.INVISIBLE);

        Drawable draw;
        draw = getDrawable(R.drawable.ehseg_bar);
        ehseg.setProgressDrawable(draw);
        ehseg.setMin(0);
        ehseg.setMax(100);
        ehseg.setProgress(50);
        ProgressRun hungerRun=new ProgressRun(720000,ehseg,2,levelDisplay);
        hungerRun.fuss();

        draw = getDrawable(R.drawable.szomj_bar);
        szomj.setProgressDrawable(draw);
        szomj.setMin(0);
        szomj.setMax(100);
        szomj.setProgress(50);
        ProgressRun thirstRun=new ProgressRun(540000,szomj,3,levelDisplay);
        thirstRun.fuss();

        draw = getDrawable(R.drawable.moral_bar);
        moral.setProgressDrawable(draw);
        moral.setMin(0);
        moral.setMax(100);
        moral.setProgress(10);

        elsoKari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.loadUrl("file:///android_asset/1.gif");
                karakter=1;
                if(mutato!=karakter) changeKarakter=true;
            }
        });
        masodikKari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.loadUrl("file:///android_asset/2.gif");
                karakter=2;
                if(mutato!=karakter) changeKarakter=true;
            }
        });
        harmadikKari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.loadUrl("file:///android_asset/3.gif");
                karakter=3;
                if(mutato!=karakter) changeKarakter=true;
            }
        });

        nev.setText(mentes.getString("playerNev","Player1"));
        playerNev.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                player=playerNev.getText().toString();
                nev.setText(player);
                changeName=true;
            }
        });

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) hatter.start();
                else hatter.pause();
                soundy=isChecked;
                changeSound=true;
            }
        });

        effectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               dropView.setEffectSound(isChecked);
               snakeView.setEffectSound(isChecked);
               flappyView.setEffectSound(isChecked);
               effecty=isChecked;
               changeEffect=true;
            }
        });
    }

    @Override
    protected void onPause() {
        save();
        super.onPause();
        hatter.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        hatter.start();
        hatter.isLooping();
    }
    @Override
    protected void onDestroy() {
        save();
        super.onDestroy();

    }
}
