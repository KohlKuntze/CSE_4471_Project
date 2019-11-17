package com.company.network;

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

    public static List<NetworkDevice> getDevices() throws IOException {
        String table = getARPTable(ARP_GET_IP_HW );
        System.out.println(table);

        List<NetworkDevice> devices;

        String operatingSystemName = System.getProperty("os.name");

        if (operatingSystemName.equals("Mac OS X")) {
            System.out.println("You have a Mac");
            devices = getNetworkDevicesMac(table);
        } else {
            System.out.println("You have a Windows");
            devices = getNetworkDevicesWindows(table);
        }

        return devices;
    }

    private static List<NetworkDevice> getNetworkDevicesWindows(String table) {
        String[] arr = table.split(" ");
        List<String> list = Arrays.asList(arr);
        List<String> nums = new ArrayList<>();
        List<NetworkDevice> devices = new ArrayList<>();

        int c = 0;
        while(c < list.size()){
            if(list.get(c).matches(".*\\d.*") || list.get(c).matches("ff-ff-ff-ff-ff-ff")){
                nums.add(list.get(c));
            }
            c++;
        }

        for(int i = 0; i<nums.size(); i++){
            if(nums.get(i).contains("192.168")){
                if(nums.get(i+1).contains("-")){
                    NetworkDevice cpu = new NetworkDevice(nums.get(i), nums.get((i+1)));
                    devices.add(cpu);
                }
            }
        }
        return devices;
    }

    private static List<NetworkDevice> getNetworkDevicesMac(String table) {
        String[] arr = table.split(" ");
        List<String> list = Arrays.asList(arr);
        List<String> ipAddresses = new ArrayList<>();
        List<String> macAddresses = new ArrayList<>();

        List<NetworkDevice> devices = new ArrayList<>();

        for (String str : list) {

            if (isMacIpAddress(str)) {
                String ipAddress = str.substring(1, str.length() - 1);
                ipAddresses.add(ipAddress);
            }

            if (isMacMacAddress(str)) {
                System.out.println(str);
                macAddresses.add(str);
            }
        }

        for (int i = 0; i < ipAddresses.size(); i++) {
            String currentIp = ipAddresses.get(i);
            String currentMac = macAddresses.get(i);

            NetworkDevice networkDevice = new NetworkDevice(currentIp, currentMac);

            devices.add(networkDevice);
        }

        return devices;
    }

    private static boolean isMacIpAddress(String str) {
        if (str.indexOf(".") > 0) {
            return true;
        }

        return false;
    }

    private static boolean isMacMacAddress(String str) {
        if (str.indexOf(":") > 0) {
            return true;
        }

        return false;
    }
}
