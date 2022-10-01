package org.example;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;


public class Game {
    private static Screen screen;
    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
            screen.clear();
            screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')
                    [0]);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     Hero hero = new  Hero(new Position(10,10));
    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }
    private void moveHero(Position position) {
        hero.setPosition(position);
    }
    private void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();

    }
    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);

        }

    }
}

