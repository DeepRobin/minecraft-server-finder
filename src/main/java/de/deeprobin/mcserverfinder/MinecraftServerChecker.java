package de.deeprobin.mcserverfinder;

import de.deeprobin.mcserverfinder.filter.MinecraftServerFilter;
import de.deeprobin.mcserverfinder.ping.ServerListPing;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;

public class MinecraftServerChecker {

    private IPLoopingAddress address;

    public MinecraftServerChecker(String ip) {
        this(new IPLoopingAddress(ip));
    }

    public MinecraftServerChecker(IPLoopingAddress address) {
        this.address = address;
    }

    public boolean isServer(){
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(address.toString(), 25565), 7000);
            return true;
        } catch (IOException e) {
            return false; // Either timeout or unreachable or failed DNS lookup.
        }
    }
/*
// FIXME: the mc server ping is broken
    public boolean isServer() {
        try {
            ServerListPing ping = new ServerListPing();
            ping.setAddress(
                    new InetSocketAddress(
                            Inet4Address.getByName(address.toString()), 25565));

            ping.fetchData();
            return true;
        } catch (IOException | IllegalStateException e) {
            return false;
        }
    }

    public boolean isServer(MinecraftServerFilter... filters) {
        try {

            ServerListPing ping = new ServerListPing();
            ping.setAddress(
                    new InetSocketAddress(
                            Inet4Address.getByName(address.toString()), 25565));
            ServerListPing.StatusResponse response = ping.fetchData();
            for (MinecraftServerFilter filter : filters) {
                if (!filter.isValid(response)) {
                    return false;
                }
            }
            return true;
        } catch (IOException | IllegalStateException e) {
            return false;
        }
    }
    */

}
