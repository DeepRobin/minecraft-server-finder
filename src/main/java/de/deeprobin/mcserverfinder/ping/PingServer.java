package de.deeprobin.mcserverfinder.ping;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PingServer {

    private String address;
    private int port;
    private int timeout;

    private boolean online;
    private int playercount;
    private int maxplayers;
    private String motd;

    public PingServer(String address, int port, int timeout){
        this.address = address;
        this.port = port;
        this.timeout = timeout;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public int getPort(){
        return this.port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public int getTimeout(){
        return this.timeout;
    }

    public void setTimeout(int timeout){
        this.timeout = timeout;
    }

    public boolean isOnline(){
        return this.online;
    }

    private void setOnline(boolean online){
        this.online = online;
    }

    public int getPlayerCount(){
        return this.playercount;
    }

    private void setPlayerCount(int playercount){
        this.playercount = playercount;
    }

    public int getMaxPlayers(){
        return this.maxplayers;
    }

    private void setMaxPlayers(int maxplayers){
        this.maxplayers = maxplayers;
    }

    public String getMotd(){
        return this.motd;
    }

    private void setMotd(String motd){
        this.motd = motd;
    }

    public void ping(){
        try{
            Socket socket = new Socket();
            OutputStream outputStream;
            DataOutputStream dataOutputStream;
            InputStream inputStream;
            InputStreamReader inputStreamReader;
            socket.setSoTimeout(this.timeout);
            socket.connect(new InetSocketAddress(this.getAddress(), this.getPort()), this.getTimeout());
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_16BE);
            dataOutputStream.write(new byte[]{(byte) 0xFE,(byte) 0x01});
            int packetId = inputStream.read();
            if(packetId == -1){
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("Premature end of stream.");
            }
            if(packetId != 0xFF){
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("Invalid packet ID (" + packetId + ").");
            }
            int length = inputStreamReader.read();
            if(length == -1){
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("Premature end of stream.");
            }
            if(length == 0){
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("Invalid string length.");
            }
            char[] chars = new char[length];
            if(inputStreamReader.read(chars,0,length) != length){
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("Premature end of stream.");
            }
            String string = new String(chars);
            if(string.startsWith("§")){
                String[] data = string.split("\0");
                this.setMotd(data[3]);
                this.setPlayerCount(Integer.parseInt(data[4]));
                this.setMaxPlayers(Integer.parseInt(data[5]));
                socket.close();
            }
            else{
                String[] data = string.split("§");
                this.setMotd(data[0]);
                this.setPlayerCount(Integer.parseInt(data[1]));
                this.setMaxPlayers(Integer.parseInt(data[2]));
                socket.close();
            }
            dataOutputStream.close();
            outputStream.close();
            inputStreamReader.close();
            inputStream.close();
            socket.close();
        } catch (IOException exception) {
            this.setOnline(false);

        }
    }

}
