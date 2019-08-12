package de.deeprobin.mcserverfinder.filter;

import de.deeprobin.mcserverfinder.ping.ServerListPing;

public interface MinecraftServerFilter {

    public boolean isValid(ServerListPing.StatusResponse response);

}
