package org.example;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena {
    private int height;
    private int width;
    public Arena(int height_,int width_){
        height=height_;
        width=width_;
        this.walls=createWalls();
        this.coins=createCoins();
        this.monsters = createMonsters();
    }
    Hero hero=new Hero(new Position(10,10));
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position_) {
        if (position_.getX() < 0) return false;
        if (position_.getX() > width - 1) return false;
        if (position_.getY() < 0) return false;
        if (position_.getY() > height - 1) return false;
        for(Wall w:walls){
            if (w.getPosition().equals(position_)){
                return false;
            }
        }
        return true;
    }


    void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        Screen screen = Game.screen;
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
        retrieveCoins();
        verifyMonsterCollisions();
        moveMonsters();
        verifyMonsterCollisions();
    }
    private  List<Monster> monsters;
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }

    private void verifyMonsterCollisions() {
        for (Monster monster : monsters)
            if (hero.getPosition().equals(monster.getPosition())) {
                System.out.println("You died!");
                System.exit(0);
            }
    }
    private void moveMonsters() {
        for (Monster monster : monsters) {
            Position monsterPosition = monster.move();
            if (canHeroMove(monsterPosition))
                monster.setPosition(monsterPosition);
        }
    }


    void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin:coins)
            coin.draw(graphics);
        for (Monster monster : monsters)
            monster.draw(graphics);

    }

    private List<Wall> walls= new ArrayList<>();
    private  List<Coin> coins;
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }
    private void retrieveCoins() {
        for (Coin coin : coins)
            if (hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
    }
}


