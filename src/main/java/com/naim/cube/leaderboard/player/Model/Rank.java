package com.naim.cube.leaderboard.player.Model;

public class Rank {

    private int currentRank =0;
    private int pereviousRank=0;

    public Rank(){

    }

    public Rank(Rank rank){
        this.currentRank = rank.getCurrentRank();
        this.pereviousRank = rank.getPereviousRank();
    }


    public int getCurrentRank() {

        return currentRank;
    }

    public void setCurrentRank(int inCommingRank) {
        if(currentRank !=0){
            pereviousRank = currentRank;
        }
        this.currentRank = inCommingRank;
    }

    public int getPereviousRank() {
        return pereviousRank;
    }


    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Rank)) {
            return false;
        }

        Rank rank = (Rank) o;

        return rank.currentRank == currentRank &&
                rank.pereviousRank == pereviousRank;
    }


    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + currentRank;
        result = 31 * result + pereviousRank;
        return result;
    }
}
