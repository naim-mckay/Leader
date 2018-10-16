package com.naim.cube.leaderboard.player.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {


    Player rick;

    @Before
    public void setUp(){
        rick = new PlayerImpl("rick");
    }



    @Test
    public void getterAndSetterCorrectness() {
        rick.addScore(13,1);
        rick.setPlayerRanking(1);
        rick.addScore(10,2);
        rick.setPlayerRanking(2);

        assertEquals ("rick",rick.getName());
        assertEquals(new Integer(10),new Integer(rick.getCurrentGameScore().getGameScore()));
        assertEquals(13,rick.getHighestScore().getGameScore());
        assertEquals(2,rick.getScores().size());
        assertEquals(2,rick.getPlayerRanking().getCurrentRank());
    }
}
