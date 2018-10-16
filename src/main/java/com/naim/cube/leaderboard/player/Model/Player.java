package com.naim.cube.leaderboard.player.Model;

import java.util.*;

public interface Player {
    public String getName();
    public Rank getPlayerRanking();
    public void setPlayerRanking(int currentPlayerRanking);
    public void addScore(int gamescore, int gameNumber);
    public Score getCurrentGameScore();
    public Score getHighestScore();
    public Map<Integer,Score> getScores();
}
