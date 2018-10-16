package com.naim.cube.leaderboard.commands.factory;

public class NoSuchCommandException extends RuntimeException {

    public NoSuchCommandException(String message) {
        super(message);
    }
}