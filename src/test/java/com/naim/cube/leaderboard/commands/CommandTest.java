package com.naim.cube.leaderboard.commands;

import com.naim.cube.leaderboard.GameControllerTest;
import com.naim.cube.leaderboard.commands.factory.CommandFactory;
import com.naim.cube.leaderboard.commands.factory.CommandFactoryTest;
import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.PlayerManager;
import com.naim.cube.leaderboard.player.PlayerNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static com.naim.cube.leaderboard.util.ErrorConsts.ERR_MESSAGE_PLAYER_NOT_FOUND;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandTest extends GameControllerTest {

    CommandFactory factory = CommandFactory.getInstance();
    List<Player> playerList;

    @Mock
    PlayerManager playerManager;



    @Before
    public void setUp(){
        iniPlayers();
        iniPlayerScoresAndRounds();
        playerList = Arrays.asList(playerKemi, playerJon, playerNaima,playerNick);
    }

    @Test
    public void testScoreCommand_addingUserFirstScore_addAScore() {
        String expectedResult = "Play 1 - "+NEWPLAYER+" enters the leaderboard at rank 1";

        when(playerManager.addPlayerScore(NEWPLAYER,6)).thenReturn(playerNew);
        when(playerManager.getCurrentPlayNumber()).thenReturn(1);
        Command command = factory.getCommandInstance(CommandFactoryTest.SCORE);
        String result = command.execute(CommandFactoryTest.SCORE+" "+NEWPLAYER+" "+6,playerManager);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testScoreCommand_addingUserSecondHigherScore_ShouldRiseUsers() {
        String expectedResult = "Play 4 - naima climbs from rank 3 to 1 above kemi and nick and jon";
        playerNaima.setPlayerRanking(1);
        List<Player> playerRetList =  Arrays.asList(playerKemi,playerNick,playerJon);
        when(playerManager.addPlayerScore(NAIMA,25)).thenReturn(playerNaima);
        when(playerManager.getCurrentPlayNumber()).thenReturn(4);
        when(playerManager.getThePlayersAboveRanking(playerNaima)).thenReturn(playerRetList);
        Command command = factory.getCommandInstance(CommandFactoryTest.SCORE);
        String result = command.execute(CommandFactoryTest.SCORE+" "+NAIMA+" "+25,playerManager);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testScoreCommand_addingUserSecondScoreOfSameValue_UserStaysSameRanking() {
        String expectedResult = "Play 4 - naima  stays at rank 3";
        playerNaima.setPlayerRanking(3);
        when(playerManager.addPlayerScore(NAIMA,25)).thenReturn(playerNaima);
        when(playerManager.getCurrentPlayNumber()).thenReturn(4);
        Command command = factory.getCommandInstance(CommandFactoryTest.SCORE);
        String result = command.execute(CommandFactoryTest.SCORE+" "+NAIMA+" "+25,playerManager);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testScoreCommand_addingUserSecondLowerScore_ShouldDropBelowUsers() {
        String expectedResult = "Play 4 - naima  drops from  rank 2 to 3 below kemi and nick";
        List<Player> playerRetList =  Arrays.asList(playerKemi,playerNick);
        when(playerManager.addPlayerScore(NAIMA,6)).thenReturn(playerNaima);
        when(playerManager.getCurrentPlayNumber()).thenReturn(4);
        when(playerManager.getThePlayersBelowRanking(playerNaima)).thenReturn(playerRetList);
        Command command = factory.getCommandInstance(CommandFactoryTest.SCORE);
        String result = command.execute(CommandFactoryTest.SCORE+" "+NAIMA+" "+6,playerManager);
        assertEquals(expectedResult, result);
    }



    @Test
    public void testUserHighestAllTimeCommand_WithUnKnownUser() throws PlayerNotFoundException {
        String expectedResult = ERR_MESSAGE_PLAYER_NOT_FOUND;
        when(playerManager.getPlayerByName(anyString())).thenThrow(new PlayerNotFoundException( ERR_MESSAGE_PLAYER_NOT_FOUND));
        Command command = factory.getCommandInstance(CommandFactoryTest.HIGHEST_SCORE);
        String result = command.execute(CommandFactoryTest.HIGHEST_SCORE+" "+JON,playerManager);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHighestAllTimeCommand_ShouldReturnWithTheHighestScore() throws PlayerNotFoundException {
        String expectedResult = "kemi reached the highest overall score of 12 on play 2";
        when(playerManager.getPlayerWithTheHighestScore()).thenReturn(playerKemi);
        Command command = factory.getCommandInstance(CommandFactoryTest.HIGHEST_SCORE);
        String result = command.execute(CommandFactoryTest.HIGHEST_SCORE,playerManager);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUserHighestAllTimeCommand_ShouldReturnTheUserWithTheHighestScore() throws PlayerNotFoundException {
        String expectedResult = "jon reached their highest score of 11 on play 4";
        when(playerManager.getPlayerByName(anyString())).thenReturn(playerJon);
        Command command = factory.getCommandInstance(CommandFactoryTest.HIGHEST_SCORE);
        String result = command.execute(CommandFactoryTest.HIGHEST_SCORE+" "+JON,playerManager);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBoardCommand_ShouldReturnTheStateOfTheBoard() {
        StringBuilder builder = new StringBuilder("1  - kemi - 12"+"\n");
        builder.append("2  - jon - 11"+"\n").append("3  - naima - 10"+"\n");
        builder.append("4  - nick - 10"+"\n").append("Current Play - 4");
        when(playerManager.getCurrentPlayNumber()).thenReturn(4);
        when(playerManager.getCurrentRankedPlayerList()).thenReturn(playerList);
        Command command = factory.getCommandInstance(CommandFactoryTest.BOARD);
        String result = command.execute(CommandFactoryTest.BOARD,playerManager);
        assertEquals(builder.toString(), result);
    }

}
