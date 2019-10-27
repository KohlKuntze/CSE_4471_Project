package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Network {

    private static final String ARP_GET_IP_HW = "arp -a";

    public static String getARPTable(String cmd) throws IOException {
        Scanner s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static List<String> getIPAndMac() throws IOException {
        String table = getARPTable(ARP_GET_IP_HW );
        String[] arr = table.split(" ");
        List<String> list = Arrays.asList(arr);
        List<String> nums = new ArrayList<>();
        List<String> ipAndMac = new ArrayList<>();
        int c = 0;
        while(c < list.size()){
            if(list.get(c).matches(".*\\d.*")){
                nums.add(list.get(c));
            }
            c++;
        }

        for(int i = 0; i<nums.size(); i++){
            if(nums.get(i).contains("192.168")){
                if(nums.get(i+1).contains("-")){
                    ipAndMac.add("IP: " + nums.get(i) + "  MAC: " + nums.get(i+1));
                }
            }
        }
        if(ipAndMac.isEmpty()) ipAndMac.add("No devices connected");

        return ipAndMac;
    }
}
