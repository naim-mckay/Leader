package com.naim.cube.leaderboard.player;

public class PlayerNotFoundException extends Exception{

    String message;

    public PlayerNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
