package com.naim.cube.leaderboard.player.Model;

public class Score {

    final int gameNumber;
    final int gameScore;
    //copy constructor pattern for cloning
    public Score(Score score) {
        this.gameScore = score.getGameScore();
        this.gameNumber = score.getGameNumber();
    }

    public Score(String parentname,int gameScore,int gameNumber) {
        this.gameScore = gameScore;
        this.gameNumber = gameNumber;
    }

    public int getGameScore() {
        return gameScore;
    }

    public int getGameNumber() {
        return gameNumber;
    }


    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Score)) {
            return false;
        }

        Score score = (Score) o;

        return score.gameScore == gameScore &&
                score.gameNumber == gameNumber;
    }


    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + gameScore;
        result = 31 * result + gameNumber;
        return result;
    }
}
