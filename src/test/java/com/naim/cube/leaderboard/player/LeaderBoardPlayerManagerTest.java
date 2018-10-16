package com.naim.cube.leaderboard.player;


import com.naim.cube.leaderboard.GameControllerTest;
import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.ranking.LeaderBoardRankingManager;
import com.naim.cube.leaderboard.player.ranking.RankManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

//@RunWith(MockitoJUnitRunner.class)
public class LeaderBoardPlayerManagerTest extends GameControllerTest {




    PlayerManager leaderBoardPlayerManager = new LeaderBoardPlayerManager( new LeaderBoardRankingManager());

    @Test
    public void testAddPlayerScore_WhenTwoNewPlayersAreAdded_ShouldOnlyCreateTwoNewPlayers(){
        leaderBoardPlayerManager.addPlayerScore(NICK,12);
        leaderBoardPlayerManager.addPlayerScore(NAIMA,1);
        List<Player> list = leaderBoardPlayerManager.getCurrentRankedPlayerList();
        assertThat(list, hasSize(2));
        assertEquals(12,list.get(0).getCurrentGameScore().getGameScore());
        assertEquals(1,list.get(1).getCurrentGameScore().getGameScore());
    }

    @Test
    public void testAddPlayerScore_WhenAnOldPlayerTakesAnotherTurn_ShouldOnlyCreateTwoNewPlayersAndUpdatePlayerScore(){
        leaderBoardPlayerManager.addPlayerScore(NICK,12);
        leaderBoardPlayerManager.addPlayerScore(NAIMA,1);
        leaderBoardPlayerManager.addPlayerScore(NAIMA,15);
        List<Player> list = leaderBoardPlayerManager.getCurrentRankedPlayerList();
        assertThat(list, hasSize(2));
        assertEquals(15,list.get(0).getCurrentGameScore().getGameScore());
    }
    @Test(expected = PlayerNotFoundException.class)
    public void testGetPlayerByName_WhenPlayerDoesNotExist() throws PlayerNotFoundException{
        leaderBoardPlayerManager.addPlayerScore(NICK,12);
        leaderBoardPlayerManager.getPlayerByName("IDONTEXIST");
    }

    @Test
    public void testGetPlayerByName_WhenPlayerExist() throws PlayerNotFoundException{
        leaderBoardPlayerManager.addPlayerScore(NICK,12);
        leaderBoardPlayerManager.addPlayerScore(NAIMA,1);
        Player player = leaderBoardPlayerManager.getPlayerByName(NICK);
        assertNotNull(player);
    }

    public void getPlayerWithTheHighestScore_shouldReturnPlayerWithTheHigestScore() throws PlayerNotFoundException{
        leaderBoardPlayerManager.addPlayerScore(NICK,12);
        leaderBoardPlayerManager.addPlayerScore(NAIMA,1);
        leaderBoardPlayerManager.addPlayerScore(NAIMA,15);
        leaderBoardPlayerManager.addPlayerScore(JON,25);
        Player player = leaderBoardPlayerManager.getPlayerWithTheHighestScore();
        //in this test it should be jon
        assertEquals(JON,player.getName());
    }
}
