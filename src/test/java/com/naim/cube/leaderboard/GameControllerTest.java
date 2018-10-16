package com.naim.cube.leaderboard;

import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.Model.PlayerImpl;

public class GameControllerTest {
    protected static String NICK="nick",KEMI="kemi",NAIMA="naima",JON="jon",NEWPLAYER = "newplayer";
    protected Player playerNick, playerKemi, playerNaima, playerJon,playerNew;

    public  void iniPlayers(){
        playerNick = new PlayerImpl(NICK);
        playerKemi = new PlayerImpl(KEMI);
        playerNaima = new PlayerImpl(NAIMA);
        playerJon = new PlayerImpl(JON);
        playerNew = new PlayerImpl(NEWPLAYER);
    }

    public  void iniPlayerScoresAndRounds(){
        playerNick.addScore(10,1);
        playerNick.setPlayerRanking(1);
        playerKemi.addScore(12,2);
        playerKemi.setPlayerRanking(1);
        playerNick.setPlayerRanking(2);
        playerNaima.addScore(10,3);
        playerNaima.setPlayerRanking(2);
        playerKemi.setPlayerRanking(1);
        playerNick.setPlayerRanking(3);
        playerJon.addScore(11,4);
        playerJon.setPlayerRanking(2);
        playerNaima.setPlayerRanking(3);
        playerKemi.setPlayerRanking(1);
        playerNick.setPlayerRanking(4);
        playerNew.setPlayerRanking(1);
    }
}