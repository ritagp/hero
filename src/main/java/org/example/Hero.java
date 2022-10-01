package org.example;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x_;
    private int y_;
    public Hero(int x, int y) {
        x_=x;
        y_=y;
    }
    public int getX(){
        return x_;
    }

    public int getY(){
        return y_;
    }

    public void setX(int x){
        x_=x;
    }
    public void setY(int y){
        y_=y;
    }

    public void draw(Screen screen){
        screen.setCharacter(x_,y_, TextCharacter.fromCharacter('X')[0]);
    }

    public void moveUp(){
        y_-=1;
    }
    public void moveDown(){
        y_+=1;
    }
    public void moveRight(){
        x_+=1;
    }
    public void moveLeft(){
        x_-=1;
    }
}