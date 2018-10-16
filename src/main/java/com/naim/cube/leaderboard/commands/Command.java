package com.naim.cube.leaderboard.commands;

import com.naim.cube.leaderboard.player.PlayerManager;

public interface Command {
    String execute(String instruction, PlayerManager playerManager);
}
