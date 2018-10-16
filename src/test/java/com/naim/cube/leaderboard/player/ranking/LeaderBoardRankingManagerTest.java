package com.naim.cube.leaderboard.player.ranking;

import com.naim.cube.leaderboard.GameControllerTest;
import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.Model.PlayerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class LeaderBoardRankingManagerTest extends GameControllerTest {


    private RankManager leaderBoardRankanager = new LeaderBoardRankingManager();
    Map<String, Player> playerMap;

    @Before
    public void setUp() {
        iniPlayers();
        int gameCounter = 1;
        playerMap = new HashMap();

        playerNick.addScore(10,gameCounter);
        playerMap.put(NICK, playerNick);
        leaderBoardRankanager.rankThePlayers(gameCounter++, playerMap);

        playerKemi.addScore(12,gameCounter);
        playerMap.put(KEMI, playerKemi);
        leaderBoardRankanager.rankThePlayers(gameCounter++, playerMap);

        playerNaima.addScore(10,gameCounter);
        playerMap.put(NAIMA, playerNaima);
        leaderBoardRankanager.rankThePlayers(gameCounter++, playerMap);

        playerJon.addScore(11,gameCounter);
        playerMap.put(JON, playerJon);
        leaderBoardRankanager.rankThePlayers(gameCounter++, playerMap);
    }


    @Test
    public void testRankThePlayers_addPlayerWithTopScoreAndOrder_ShouldReturn_ListWithNewPlayerOnTop() {
        Player player5 = new PlayerImpl("NEWPLAYER");
        player5.addScore(17, 5);
        playerMap.put("NEWPLAYER",  player5);
        leaderBoardRankanager.rankThePlayers(5, playerMap);
        List<Player> list = leaderBoardRankanager.getThePlayersHistoryRanking(5);
        assertThat(list, hasSize(5));
        assertEquals(player5.getName(), list.get(0).getName());
    }

    @Test
    public void testRankThePlayers_addPlayerWithLowestScoreAndOrder_ShouldReturn_ListWithNewPlayerOnBottom() {
        Player player5 = new PlayerImpl("NEWPLAYER");
        player5.addScore(1, 5);
        playerMap.put("NEWPLAYER",  player5);
        leaderBoardRankanager.rankThePlayers(5, playerMap);
        List<Player> list = leaderBoardRankanager.getThePlayersHistoryRanking(5);
        assertThat(list, hasSize(5));
        assertEquals(player5.getName(), list.get(list.size() - 1).getName() );
    }

    @Test
    public void testRankThePlayers_updatePlayerWithTopScoreAndOrder_ShouldReturn_ListWithNewPlayerOnTop() {
        Player player5 = playerMap.get(NICK);
        player5.addScore(15, 5);
        leaderBoardRankanager.rankThePlayers(5, playerMap);
        List<Player> list = leaderBoardRankanager.getThePlayersHistoryRanking(5);
        assertThat(list, hasSize(4));
        assertEquals(playerNick.getName(), list.get(0).getName());
    }


    @Test
    public void testGetThePlayersHistoryRanking_shouldreturnStateOfAGame() {
        List<Player> list = leaderBoardRankanager.getThePlayersHistoryRanking(3);
        //at turn 3 should be kemi followed by naima  as naima goes before nick due to name
        assertEquals(playerKemi.getName(), list.get(0).getName() );
        assertEquals( playerNaima.getName(), list.get(1).getName());
    }
}