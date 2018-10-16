package com.naim.cube.leaderboard.util.comparators;

import com.naim.cube.leaderboard.GameControllerTest;
import com.naim.cube.leaderboard.player.Model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.util.*;

import static org.junit.Assert.assertThat;


public class PlayerComparatorTest extends GameControllerTest {

    protected Map<String, Player> playerMap;

    @Before
    public void setUp(){
        playerMap = new HashMap();
        iniPlayers();
        iniPlayerScoresAndRounds();
    }

    @Test
    public void testComparator_shouldOrderListByCurrentScoreThenName() {
        playerMap.put(NICK,playerNick);
        playerMap.put(KEMI,playerKemi);
        playerMap.put(NAIMA,playerNaima);
        playerMap.put(JON,playerJon);

        List<Player> orderdPlayers = new ArrayList<Player>(playerMap.values());
        Collections.sort(orderdPlayers, new PlayerComparator());
        // Ensure Correct order    playerKemi ,  playerJon ,  playerNaima and playerNick
        assertThat(orderdPlayers, contains(playerKemi, playerJon, playerNaima, playerNick));
    }
}
