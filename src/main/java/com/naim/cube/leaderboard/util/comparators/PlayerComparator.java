package com.naim.cube.leaderboard.util.comparators;

import com.naim.cube.leaderboard.player.Model.Player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player>  {

    public PlayerComparator(){}

    @Override
    public int compare(Player p1, Player p2) {
        if(p1.getCurrentGameScore().getGameScore() == p2.getCurrentGameScore().getGameScore()){
            return  p1.getName().compareToIgnoreCase(p2.getName());
        }else {
            return (p1.getCurrentGameScore().getGameScore() < p2.getCurrentGameScore().getGameScore()) ? 1 : -1;
        }
    }
}
