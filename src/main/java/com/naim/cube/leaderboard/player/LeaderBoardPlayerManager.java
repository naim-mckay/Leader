package com.naim.cube.leaderboard.player;


import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.Model.PlayerImpl;
import com.naim.cube.leaderboard.player.ranking.RankManager;

import java.util.*;

import static com.naim.cube.leaderboard.util.ErrorConsts.ERR_MESSAGE_PLAYERS_DONT_EXSIT;
import static com.naim.cube.leaderboard.util.ErrorConsts.ERR_MESSAGE_PLAYER_NOT_FOUND;

final public class LeaderBoardPlayerManager implements PlayerManager{

    static private int gameCounter = 0;

    final Map<String, Player> playerMap = new HashMap();

    final RankManager rankManager;

    public LeaderBoardPlayerManager(RankManager rankManager){
        this.rankManager = rankManager;
    }

    @Override
    public int getCurrentPlayNumber(){
        int mygameCounter = gameCounter;
        return mygameCounter;
    }

    @Override
    public Player addPlayerScore(String playerName ,int playerScore){
        Player player;
        try {
            player = getPlayerByName(playerName);
        }catch (PlayerNotFoundException e){
            player = new PlayerImpl(playerName);
            playerMap.put(playerName,player);
        }
        player.addScore(playerScore,++ gameCounter);
        rankManager.rankThePlayers(gameCounter,playerMap);
        return player;
    }

    @Override
    public Player getPlayerByName(String playerName) throws PlayerNotFoundException{
        Player player = (playerMap.containsKey(playerName)) ? playerMap.get(playerName) : null;
        if(player == null) throw new PlayerNotFoundException( ERR_MESSAGE_PLAYER_NOT_FOUND);
        return player;
    }

    @Override
    public Player getPlayerWithTheHighestScore() throws PlayerNotFoundException{
        Player player = null;
        for (Player tempPlayer : playerMap.values()) {
            if ((player==null) ||
                    (tempPlayer.getHighestScore().getGameScore() > player.getHighestScore().getGameScore())){
                player = tempPlayer;
            }
        }
        if(player==null)throw new PlayerNotFoundException(ERR_MESSAGE_PLAYERS_DONT_EXSIT);
        return player;
    }


    @Override
    public List<Player> getThePlayersAboveRanking(Player player){
        return rankManager.getThePlayersAboveRanking(gameCounter,player);
    }

    @Override
    public List<Player> getThePlayersBelowRanking(Player player){
        return rankManager.getThePlayersBelowRanking(gameCounter,player);
    }

    @Override
    public List<Player> getCurrentRankedPlayerList(){
        return rankManager.getThePlayersHistoryRanking(gameCounter);
    }

    @Override
    public List<Player> getRankedPlayerListHistoryAt(int position){
        return rankManager.getThePlayersHistoryRanking(position);
    }

}
