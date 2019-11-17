package com.company.network;

import java.io.IOException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        OSButtonViewer.getComputer();
    }

    public static void windows() throws IOException{
        List<NetworkDevice> devices = Network.getDevices();

        System.out.println("***** ALL DEVICES ON THE NETWORK *****");
        if(devices.isEmpty()){
            System.out.println("No devices connected");
        }else{
            for (NetworkDevice device : devices){
                String mac = device.getMAC();
                String ip = device.getIP();
                AcceptRejectButtons.AcceptOrReject(mac, ip);
            }
        }
    }

    public static void macLinux() throws IOException{
        List<NetworkDevice> devices = Network.getDevices();

        System.out.println("***** ALL DEVICES ON THE NETWORK *****");
        if(devices.isEmpty()){
            System.out.println("No devices connected");

        }else{
            for (NetworkDevice device : devices){
                String mac = device.getMAC();
                String ip = device.getIP();
                AcceptRejectButtons.AcceptOrReject(mac, ip);
            }
        }
    }


}
