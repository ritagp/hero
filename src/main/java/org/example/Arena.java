package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int height;
    private int width;
    public Arena(int height_,int width_){
        height=height_;
        width=width_;
    }
    Hero hero=new Hero(new Position(10,10));
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position_) {
        if (position_.getX()<width || position_.getY()<width) {
            return true;
        }
        else {
            return false;
        }
    }

    void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        Screen screen = null;
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();

    }
    void draw(Screen screen) throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

}