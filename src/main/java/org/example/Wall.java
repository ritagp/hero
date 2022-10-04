package org.example;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall  extends Element{
    private int x_;
    private int y_;

    private Position position=new Position(x_,y_);

    public Wall(int x_, int y_) {
        super(x_,y_);
    }

    public  Position getPosition(){
        return position;
    }

    public void setPosition(Position position_){
        position=position_;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "|");
    }


}
