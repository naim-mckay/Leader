package com.naim.cube.leaderboard.player.Model;

import java.util.HashMap;
import java.util.Map;

public class PlayerImpl implements Player {


    final String name;
    Map<Integer,Score> scoresMap = new HashMap();
    Score currentScore;

    Rank playerRank = new Rank();

    public PlayerImpl(String name){
      this.name = name;
    }

    public PlayerImpl(Player player){
        this.name = player.getName();
        this.scoresMap = player.getScores();
        this.currentScore = player.getCurrentGameScore();
        this.playerRank = player.getPlayerRanking();
    }


    public String getName() {
        return name;
    }

    public Rank getPlayerRanking() {

        return new Rank(playerRank);
    }

    public void setPlayerRanking(int currentPlayerRanking) {
        playerRank.setCurrentRank(currentPlayerRanking);
    }

    public void addScore(int gamescore, int gameNumber){
        Score score = new Score(name,gamescore,gameNumber);
        scoresMap.put(gameNumber,score);
        currentScore = scoresMap.get(gameNumber);
    }

    public Score getCurrentGameScore() {
        return new Score(currentScore);
    }

    public Score getHighestScore(){
        Score maxScore =null;
        for (Map.Entry<Integer,Score> entry : scoresMap.entrySet()) {
            if(maxScore == null || entry.getValue().getGameScore() > maxScore.getGameScore()){
                maxScore = entry.getValue();
            }
        }
        return new Score(maxScore);
    }



    public Map<Integer,Score>  getScores(){
        Map<Integer,Score> scoresMapCopy = new HashMap();
        for (Map.Entry<Integer,Score> entry : scoresMap.entrySet()) {
            scoresMapCopy.put(entry.getKey(), new Score(entry.getValue()));
        }
        return scoresMapCopy;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof PlayerImpl)) {
            return false;
        }

        PlayerImpl player = (PlayerImpl) o;

        return player.name.equals(name);
    }


    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + currentScore.hashCode();
        return result;
    }
}
