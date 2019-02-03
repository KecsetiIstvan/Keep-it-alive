package com.example.kecseti.kislenyprojekt.Tiles;

public class PipeTile {
    int x,y,height, width,gapSize,gapY;
    public PipeTile(int height,int width){
        this.height=height;
        this.width=width;
    }
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setGapSize(int gapSize){this.gapSize=gapSize;}
    public void setGapY(int gapY)      {this.gapY=gapY;      }
    public int  getGapSize()           {return this.gapSize; }
    public int  getGapY()              {return this.gapY;    }
    public int  getX()                 {return x;            }
    public int  getY()                 {return y;            }
    public int  getHeight()            {return height;       }
    public int  getWidth()             {return width;        }
}
