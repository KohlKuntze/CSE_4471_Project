package com.company.ui.utilities;

import com.company.DataBase.SQLiteDB;
import com.company.network.AcceptRejectButtons;
import com.company.network.NetworkDevice;
import com.company.ui.view.ProjectScrollPanel;
import com.company.ui.view.ProjectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.company.DataBase.SQLiteDB.*;

import static com.company.network.Network.getDevices;

public class ScrollPanelUtilties {

    public static void updateView(ProjectView view) throws IOException {
        List<NetworkDevice> networkDeviceList = getNetworkDeviceList();

        List<String> unknownDeviceList = getUnknownDevices(networkDeviceList).stream()
                .map(networkDevice -> networkDevice.getMAC())
                .collect(Collectors.toList());

        List<String> knownDeviceList = getKnownDevices(networkDeviceList).stream()
                .map(networkDevice -> networkDevice.getMAC())
                .collect(Collectors.toList());


        view.updateUnknownIpAddressList(unknownDeviceList);
        view.updateKnownIpAddressList(knownDeviceList);

    }

    public static void updateUnknownDeviceScrollPanel(ProjectScrollPanel scrollPanel) throws IOException {
        List<NetworkDevice> networkDeviceList = getNetworkDeviceList();

        List<String> unknownDeviceList = getUnknownDevices(networkDeviceList).stream()
                .map(networkDevice -> networkDevice.getMAC())
                .collect(Collectors.toList());

        scrollPanel.updateMacAddresses(unknownDeviceList);
    }

    public static void updateKnownDeviceScrollPanel(ProjectScrollPanel scrollPanel) throws IOException {
        List<NetworkDevice> networkDeviceList = getNetworkDeviceList();

        List<String> knownDeviceList = getKnownDevices(networkDeviceList).stream()
                .map(networkDevice -> networkDevice.getMAC())
                .collect(Collectors.toList());

        scrollPanel.updateMacAddresses(knownDeviceList);
    }

    private static List<NetworkDevice> getUnknownDevices(List<NetworkDevice> devices) throws IOException {
        List<NetworkDevice> unknownDeviceList = new ArrayList<>();
        Set<String> knownDevices = SQLiteDB.getPermittedDevices();

        for (int i = 0; i < devices.size(); i++) {
            NetworkDevice currentDevice = devices.get(i);

            if (!knownDevices.contains(currentDevice)) {
                unknownDeviceList.add(currentDevice);
            }
        }

        return unknownDeviceList;
    }

    private static List<NetworkDevice> getKnownDevices(List<NetworkDevice> devices) throws IOException {
        List<NetworkDevice> knownDeviceList = new ArrayList<>();
        Set<String> knownDevices = SQLiteDB.getPermittedDevices();

        for (int i = 0; i < devices.size(); i++) {
            NetworkDevice currentDevice = devices.get(i);

            if (knownDevices.contains(currentDevice)) {
                knownDeviceList.add(currentDevice);
            }
        }

        return knownDeviceList;
    }

    private static List<NetworkDevice> getNetworkDeviceList() {
        System.out.println("Getting network devices");
        List<NetworkDevice> networkDeviceList = new ArrayList<>();

        try {
            networkDeviceList = getDevices();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return networkDeviceList;
    }
}