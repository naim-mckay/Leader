package com.naim.cube.leaderboard.commands;

import com.naim.cube.leaderboard.player.Model.Rank;
import com.naim.cube.leaderboard.util.ErrorConsts;
import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.PlayerManager;
import com.naim.cube.leaderboard.util.Utils;

import java.util.Iterator;
import java.util.List;

public class ScoreCommand  implements Command{
    @Override
    public String execute(String instruction, PlayerManager playerManager) {
        StringBuilder builder = new StringBuilder();
        String[] instr = instruction.split(" ");
        if(instr.length != 3){
            builder.append(ErrorConsts.ERR_MESSAGE_NO_USER_NAME_OR_SCORE);
        }else {
            if (Utils.isNumber(instr[2].trim())) {
                int score = new Integer(instr[2]);
                Player currentPlayer = playerManager.addPlayerScore(instr[1],score);
                builder.append("Play " + playerManager.getCurrentPlayNumber() + " - " + currentPlayer.getName() + " ");

                Rank playerRack = currentPlayer.getPlayerRanking();

                if (playerRack.getPereviousRank() == 0) {
                    builder.append("enters the leaderboard at rank " + playerRack.getCurrentRank());
                } else {
                    if (playerRack.getCurrentRank() == playerRack.getPereviousRank()) {
                        builder.append(" stays at rank " + playerRack.getCurrentRank());
                    } else {
                        builder.append((playerRack.getCurrentRank() > playerRack.getPereviousRank()) ? " drops from " : "climbs from");
                        builder.append(" rank " + playerRack.getPereviousRank());
                        builder.append(" to " + playerRack.getCurrentRank());

                        List<Player> list;
                        if (playerRack.getCurrentRank() > playerRack.getPereviousRank()) {
                            list = playerManager.getThePlayersBelowRanking(currentPlayer);
                            if (!list.isEmpty()) builder.append(" below ");
                        } else {
                            list = playerManager.getThePlayersAboveRanking(currentPlayer);
                            if (!list.isEmpty()) builder.append(" above ");
                        }

                        Iterator<Player> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            Player player = iterator.next();
                            builder.append(player.getName());
                            if (iterator.hasNext()) {
                                builder.append(" and ");
                            }
                        }
                    }
                }
            }else {
                builder.append(ErrorConsts.ERR_MESSAGE_NO_SCORE);
            }
        }

        return builder.toString();
    }
}
