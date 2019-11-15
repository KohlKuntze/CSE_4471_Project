package com.company.network;

import java.io.IOException;
import java.util.List;

import com.company.network.Network;

public class Main {

    public static void main(String[] args) throws IOException {
        ButtonViewer.getComputer();

    }

    public static void windows() throws IOException{
        List<String> ipAndMac = Network.getIPAndMac();

        System.out.println("***** ALL DEVICES ON THE NETWORK *****");
        for(String address : ipAndMac){
            System.out.println(address);
        }
    }

    public static void mac_linux() throws IOException{
        // TODO

        System.out.println("To be implemented");
    }

}
