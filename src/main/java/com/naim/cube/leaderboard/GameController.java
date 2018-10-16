package com.naim.cube.leaderboard;

import com.naim.cube.leaderboard.commands.Command;
import com.naim.cube.leaderboard.commands.factory.CommandFactory;
import com.naim.cube.leaderboard.commands.factory.NoSuchCommandException;
import com.naim.cube.leaderboard.player.LeaderBoardPlayerManager;
import com.naim.cube.leaderboard.player.PlayerManager;
import com.naim.cube.leaderboard.player.ranking.LeaderBoardRankingManager;
import com.naim.cube.leaderboard.player.ranking.RankManager;

import java.util.Scanner;

public class GameController {
    RankManager    rankManager    = new LeaderBoardRankingManager();
    PlayerManager  playerManager  = new LeaderBoardPlayerManager(rankManager);
    CommandFactory factory = CommandFactory.getInstance();
    Scanner scanner = new Scanner(System.in);


    public  GameController(){
    }


    public String executeCommand(String lineCommand){
        try {
            Command command = factory.getCommandInstance(lineCommand);
            return command.execute(lineCommand,playerManager);
        }catch (NoSuchCommandException e){
            return e.getMessage();
        }
    }

    public void beginGame(){
        while (true) {
            System.out.println("Enter command type 'q' to quit : ");
            String input = scanner.nextLine();
            if ("q".equals(input)) {
                System.out.println("Exit!");
                break;
            }
            System.out.println(executeCommand(input));
        }
        scanner.close();
    }

    public static void main(String[] args) {
        GameController  gameController = new  GameController();
        gameController.beginGame();
    }
}
