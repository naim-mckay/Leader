package com.naim.cube.leaderboard.player;

import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.Model.Score;

import java.util.List;

public interface PlayerManager {
    public Player addPlayerScore(String playerName ,int playerScore);
    public int getCurrentPlayNumber();
    public Player getPlayerByName(String playername) throws PlayerNotFoundException;
    public Player getPlayerWithTheHighestScore() throws PlayerNotFoundException;
    public List<Player> getCurrentRankedPlayerList();
    public List<Player> getThePlayersAboveRanking(Player player);
    public List<Player> getThePlayersBelowRanking(Player player);
    public List<Player> getRankedPlayerListHistoryAt(int position);
}
