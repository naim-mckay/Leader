package com.naim.cube.leaderboard.commands;

import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.PlayerManager;
import com.naim.cube.leaderboard.util.ErrorConsts;
import com.naim.cube.leaderboard.util.Utils;

import java.util.List;

public class BoardCommand implements Command{
    @Override
    public String execute(String instruction, PlayerManager playerManager) {
        StringBuilder builder = new StringBuilder();
        String[] instr = instruction.split(" ");
        if(instr.length ==1){
            List<Player> list = playerManager.getCurrentRankedPlayerList();
            for(Player player : list){
                builder.append(player.getPlayerRanking().getCurrentRank()+"  - "+player.getName()+" - "+player.getCurrentGameScore().getGameScore()+"\n");
            }
            builder.append("Current Play - "+playerManager.getCurrentPlayNumber());
        }else {
            if (Utils.isNumber(instr[1].trim())) {
                int game = Utils.getNumber(instr[1].trim());
                List<Player> beforePlayList = playerManager.getRankedPlayerListHistoryAt(game - 1);
                List<Player> afterPlayList = playerManager.getRankedPlayerListHistoryAt(game);
                builder.append("before play " + game + "\n");
                for (Player player : beforePlayList) {
                    builder.append(player.getPlayerRanking().getCurrentRank() + "  - " + player.getName() + " - " + player.getCurrentGameScore().getGameScore() + "\n");
                }
                builder.append("after play " + game + "\n");
                for (Player player : afterPlayList) {
                    builder.append(player.getPlayerRanking().getCurrentRank() + "  - " + player.getName() + " - " + player.getCurrentGameScore().getGameScore() + "\n");
                }
            }else {
                builder.append(ErrorConsts.ERR_MESSAGE_NO_BOARD);
            }
        }
        return builder.toString();
    }
}
