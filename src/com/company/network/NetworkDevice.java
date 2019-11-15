package com.company.network;


public class NetworkDevice {

    private String IP;
    private String MAC;

    public NetworkDevice(String IP, String MAC) {
        this.IP = IP;
        this.MAC = MAC;
    }

    public String getIP() {
        return IP;
    }

    public String getMAC() {
        return MAC;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
