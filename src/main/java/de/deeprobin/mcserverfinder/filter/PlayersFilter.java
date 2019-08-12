package de.deeprobin.mcserverfinder.filter;

import de.deeprobin.mcserverfinder.ping.ServerListPing;

public class PlayersFilter implements MinecraftServerFilter {

    private final int minPlayers;
    private final int maxPlayers;

    public PlayersFilter(int minPlayers, int maxPlayers) {
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }


    @Override
    public boolean isValid(ServerListPing.StatusResponse response) {
        int current = response.getPlayers().getOnline();
        return current >= minPlayers && current <= maxPlayers;
    }
}
