package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.company.Network;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> ipAndMac = Network.getIPAndMac();
        for(String address : ipAndMac){
            System.out.println(address);
        }
    }
    
    // private static final Integer PING_TIMEOUT = 5000;

    // public static void main(String[] args) throws IOException, InterruptedException {

    //     String ipAddress = "";

    //     for (int network = 172; network < 173; network++) {
    //         for (int subnetOne = 31; subnetOne < 32; subnetOne++) {
    //             for (int subnetTwo = 136; subnetTwo < 142; subnetTwo++) {
    //                 for (int host = 30; host < 255; host++) {
    //                     ipAddress = network + "." + subnetOne + "." + subnetTwo + "." + host;


    //                     if (host - 1 % 10 == 0) {
    //                         System.out.println("Current: " + ipAddress);
    //                     }


    //                     String macAddress = getMacAddressIfValid(ipAddress);
    //                     if (macAddress.length() > 0) {
    //                         System.out.println(ipAddress + ": " + macAddress);
    //                     }
    //                 }
    //             }
    //         }

    //     }

    // }

    // private static String getMacAddressIfValid(String ipAddress) throws IOException, InterruptedException {
    //     String macAddress = "";

    //     try {
    //         if (ping(ipAddress)) {
    //             macAddress = arpNForMacAddress(ipAddress);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return macAddress;
    // }

    // private static boolean ping(String ipAddress) throws IOException {
    //     InetAddress inetAddress = InetAddress.getByName(ipAddress);
    //     //System.out.println("Sending Ping Request to " + ipAddress);

    //     try {
    //         if (inetAddress.isReachable(PING_TIMEOUT)){
    //             return true;
    //         }
    //     } catch (ConnectException e) {
    //         return false;
    //     }


    //     return false;

    // }

    // private static String arpNForMacAddress(String ipAddress) throws IOException, InterruptedException{
    //     String command = "arp -n " + ipAddress;
    //     Process proc = Runtime.getRuntime().exec(command);

    //     String macAddress = "";
    //     // Read the output

    //     BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

    //     String line = "";
    //     while((line = reader.readLine()) != null) {
    //         if (line.contains("(" + ipAddress + ")")) {
    //             int indexOfAt = line.indexOf("at ");
    //             int indexOfMacAddress = indexOfAt + 3;

    //             while (line.charAt(indexOfMacAddress) != ' ') {
    //                 macAddress = macAddress + line.charAt(indexOfMacAddress);
    //                 indexOfMacAddress++;
    //             }

    //         }
    //         //System.out.print(line + "\n");
    //     }

    //     proc.waitFor();

    //     return macAddress;
    // }
}
