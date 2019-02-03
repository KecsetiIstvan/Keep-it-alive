package com.example.kecseti.kislenyprojekt.Tiles;

public class BasicTile {

    int x,y,width,height;

    public BasicTile(int width, int height) {
        this.width =  width;
        this.height = height;
    }
    public void setLocation(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int  getWidth()            { return width;        }
    public int  getHeight()           { return height;       }
    public int  getX()                { return x;            }
    public int  getY()                { return y;            }
    public void setHeight(int height) { this.height = height;}
    public void setWidth(int width)   { this.width = width;  }
}
