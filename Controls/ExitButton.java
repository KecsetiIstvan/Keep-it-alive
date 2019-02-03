package com.example.kecseti.kislenyprojekt.Controls;

import android.view.View;

import com.example.kecseti.kislenyprojekt.Drop.DropView;
import com.example.kecseti.kislenyprojekt.Flappy.FlappyView;
import com.example.kecseti.kislenyprojekt.Snake.SnakeView;

    public class ExitButton implements  View.OnClickListener{

        private SnakeView snakeView;
        private FlappyView flappyView;
        private DropView dropView;
        private MenuElemContainer menu;

        public ExitButton(SnakeView v, FlappyView f,DropView d,MenuElemContainer m) {
            this.snakeView = v;
            this.flappyView=f;
            this.dropView=d;
            this.menu=m;
        }
        @Override
        public void onClick(View v) {
            if (!snakeView.didLost()){
                snakeView.exit();
             }
             if(!dropView.didLost()) {
                 dropView.exit();
             }
             if(!flappyView.didLost()) {
                 flappyView.exit();
             }
            menu.show();
            menu.settingsHide();
            menu.coustomHide();
        }
    }
