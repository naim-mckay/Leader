package com.naim.cube.leaderboard.player.ranking;

import com.naim.cube.leaderboard.player.Model.Player;
import com.naim.cube.leaderboard.player.Model.PlayerImpl;
import com.naim.cube.leaderboard.util.comparators.PlayerComparator;

import java.util.*;

public class LeaderBoardRankingManager implements RankManager {

    final Map<Integer,List<Player>> rankHistoryMap = new HashMap();

    public LeaderBoardRankingManager() {
    }

    @Override
    public void rankThePlayers(int gameNumber, Map<String, Player> playerMap) {
        List<Player> rankedPlayers = orderAndRankListOfPlayersByHighestRank(playerMap);
        rankHistoryMap.put(gameNumber,rankedPlayers);
    }


    @Override
    public List<Player> getThePlayersHistoryRanking(int gameNumber) {
        return rankHistoryMap.get(gameNumber);
    }


    @Override
    public List<Player> getThePlayersBelowRanking(int gameNumber,Player player) {
        int index = rankHistoryMap.get(gameNumber).indexOf(player);
        return rankHistoryMap.get(gameNumber).subList(0, index);
    }

    @Override
    public List<Player> getThePlayersAboveRanking(int gameNumber,Player player) {
        int index = rankHistoryMap.get(gameNumber).indexOf(player);
        return rankHistoryMap.get(gameNumber).subList(index+1, rankHistoryMap.get(gameNumber).size());
    }


    public List<Player> orderAndRankListOfPlayersByHighestRank(Map<String, Player> playerMap){
        int rank=1;
        List<Player> orderdPlayers = new ArrayList<Player>(playerMap.values());
        Collections.sort(orderdPlayers, new PlayerComparator());
        List<Player> clonedList = new ArrayList<>(playerMap.size());
        for(Player player : orderdPlayers){
            player.setPlayerRanking(rank++);
           clonedList.add(new PlayerImpl(player));
        }
        return clonedList;
    }
}
