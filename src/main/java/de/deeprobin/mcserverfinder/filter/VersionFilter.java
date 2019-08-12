package de.deeprobin.mcserverfinder.filter;

import de.deeprobin.mcserverfinder.ping.ServerListPing;

public class VersionFilter implements MinecraftServerFilter {

    // TODO

    @Override
    public boolean isValid(ServerListPing.StatusResponse response) {
        return false;
    }
}
