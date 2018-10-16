package com.naim.cube.leaderboard.commands.factory;


import com.naim.cube.leaderboard.commands.BoardCommand;
import com.naim.cube.leaderboard.commands.Command;
import com.naim.cube.leaderboard.commands.HighestAllTimeCommand;
import com.naim.cube.leaderboard.commands.ScoreCommand;
import com.naim.cube.leaderboard.util.ErrorConsts;

public class CommandFactory {

    public static String HIGHEST_SCORE = "highest-alltime-score";
    public static String SCORE = "score";
    public static String BOARD = "board";


    private static CommandFactory ourInstance = new CommandFactory();

    public static CommandFactory getInstance() {
        return ourInstance;
    }

    private CommandFactory() {
    }


    public Command getCommandInstance(String inPutStr) throws NoSuchCommandException {
        if (inPutStr.startsWith(BOARD)) {
            return new BoardCommand();
        } else if (inPutStr.startsWith(SCORE)) {
            return new ScoreCommand();
        } else if (inPutStr.startsWith(HIGHEST_SCORE)) {
            return new HighestAllTimeCommand();
        } else
            throw new NoSuchCommandException(ErrorConsts.ERR_MESSAGE_NO_SUCH_COMMAND);
    }
}
