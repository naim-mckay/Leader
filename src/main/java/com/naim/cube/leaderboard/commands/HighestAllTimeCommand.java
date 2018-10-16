package com.naim.cube.leaderboard.commands;

import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.PlayerManager;
import com.naim.cube.leaderboard.player.PlayerNotFoundException;
import com.naim.cube.leaderboard.player.Model.Score;

public class HighestAllTimeCommand implements Command{
    @Override
    public String execute(String instruction, PlayerManager playerManager) {
        StringBuilder builder = new StringBuilder();
        String[] instr = instruction.split(" ");
        if(instr.length < 3) {
            try {
                Player player;
                Score  score;
                if(instr.length == 1) {
                    player = playerManager.getPlayerWithTheHighestScore();
                    score = player.getHighestScore();
                }else {
                    player = playerManager.getPlayerByName(instr[1]);
                    score  = player.getHighestScore();
                }
                builder.append(player.getName());
                builder.append(" reached ");
                builder.append((instr.length == 1) ? "the highest overall" : "their highest");
                builder.append(" score of "+score.getGameScore());
                builder.append(" on play "+score.getGameNumber());
            } catch (PlayerNotFoundException e) {
                builder.append(e.getMessage());
            }
        }
        return builder.toString();
    }
}
