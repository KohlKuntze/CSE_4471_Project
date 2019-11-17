package com.company.ui.utilities;

import com.company.network.NetworkDevice;
import com.company.ui.view.ProjectScrollPanel;
import com.company.ui.view.ProjectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.network.Network.getDevices;

public class ScrollPanelUtilties {

    public static void updateView(ProjectView view) {
        List<NetworkDevice> networkDeviceList = getNetworkDeviceList();

        List<String> unknownDeviceList = getUnknownDevices(networkDeviceList).stream()
                .map(networkDevice -> networkDevice.getMAC())
                .collect(Collectors.toList());

        view.updateUnknownIpAddressList(unknownDeviceList);

    }

    private static List<NetworkDevice> getUnknownDevices(List<NetworkDevice> devices) {
        List<NetworkDevice> unknownDeviceList = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            NetworkDevice currentDevice = devices.get(i);

            if (true /* will need to test database at some point */) {
                unknownDeviceList.add(currentDevice);
            }
        }

        return unknownDeviceList;
    }

    private static List<NetworkDevice> getNetworkDeviceList() {
        List<NetworkDevice> networkDeviceList = new ArrayList<>();

        try {
            networkDeviceList = getDevices();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return networkDeviceList;
    }
}
