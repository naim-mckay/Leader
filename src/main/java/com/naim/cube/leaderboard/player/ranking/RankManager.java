package com.naim.cube.leaderboard.player.ranking;

import com.naim.cube.leaderboard.player.Model.Player;

import java.util.List;
import java.util.Map;

public interface RankManager {
    void rankThePlayers(int gameNumber, Map<String, Player> playerMap);
    List<Player> getThePlayersHistoryRanking(int gameNumber);
    List<Player> getThePlayersAboveRanking(int gameNumber,Player player);
    List<Player> getThePlayersBelowRanking(int gameNumber,Player player);
}
