package org.example;

import org.example.Game;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game =new Game();
        game.run();
    }
}
