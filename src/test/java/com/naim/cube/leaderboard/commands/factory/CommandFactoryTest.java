package com.naim.cube.leaderboard.commands.factory;


import com.naim.cube.leaderboard.commands.Command;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandFactoryTest {

    public static String HIGHEST_SCORE = "highest-alltime-score";
    public static String SCORE = "score";
    public static String BOARD = "board";
    public static String BAD_COMMAND = "a-bad-command";


    CommandFactory factory = CommandFactory.getInstance();

    @Test(expected =  NoSuchCommandException.class)
    public void testCommandInstance_WithStringNotInFactory_ShouldThrow_NoSuchCommandException() {
        factory.getCommandInstance(BAD_COMMAND);
    }

    @Test
    public void testCommandInstance_WithStringInFactory_Should_Verify_withNo_Errors() {
        Command command1 = factory.getCommandInstance(SCORE);
        Command command2 = factory.getCommandInstance(HIGHEST_SCORE);
        Command command3 = factory.getCommandInstance(BOARD);

        assertTrue(command1 instanceof Command);
        assertTrue(command2 instanceof Command);
        assertTrue(command3 instanceof Command);
    }
}
