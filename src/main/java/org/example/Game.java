package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;


public class Game {
    static Screen screen;
    public Game() {
        try {
            TerminalSize terminalsize= new TerminalSize(40,40);
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize).createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
            screen.clear();
            screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')[0]);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     Hero hero = new  Hero(new Position(10,10));
    private void moveHero(Position position) {

        hero.setPosition(position);
    }

    Arena arena =new Arena(40,40);
    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key);
    }
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType()==KeyType.EOF)
                break;

        }

    }
}

