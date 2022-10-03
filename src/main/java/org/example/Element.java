package org.example;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private int x_;
    private int y_;
    Position position=new Position(x_,y_);

    public Element(int x_, int y_) {
        position=new Position(x_,y_);
    }

    public Element(Position position_) {
        position=position_;
    }

    public  Position getPosition(){
        return position;
    }

    public void setPosition(Position position_){
        position=position_;
    }

    public abstract void draw(TextGraphics graphics);

}
