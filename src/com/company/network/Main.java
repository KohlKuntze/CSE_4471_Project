package com.company.network;

import java.io.IOException;
import java.util.List;

import com.company.network.Network;

public class Main {

    public static void main(String[] args) throws IOException {
        ButtonViewer.getComputer();

    }

    public static void windows() throws IOException{
        List<NetworkDevice> devices = Network.getDevices();

        System.out.println("***** ALL DEVICES ON THE NETWORK *****");
        if(devices.isEmpty()){
            System.out.println("No devices connected");
        }else{
            for (NetworkDevice device : devices){
                System.out.println("IP: " + device.getIP() + "\tMAC: " + device.getMAC());
            }
        }
    }

    public static void macLinux() throws IOException{
        // TODO

        System.out.println("To be implemented");
    }



}
