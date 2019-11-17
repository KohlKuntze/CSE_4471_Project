package com.company.ui.controller;

import com.company.DataBase.SQLiteDB;
import com.company.ui.utilities.ScrollPanelUtilties;
import com.company.ui.view.ProjectScrollPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProjectActionListeners {

    public static GivePermissionActionListener getDenyPermissionActionListener() {
        return new GivePermissionActionListener();
    }

    public static RemovePermissionActionListener getPermitPermissionActionListener() {
        return new RemovePermissionActionListener();
    }

    public interface PermissionActionListener extends ActionListener {

        void actionPerformed(ActionEvent e);

        void setScrollPanel(ProjectScrollPanel scrollPanel);

        ProjectScrollPanel getScrollPane();
    }

    public static class GivePermissionActionListener implements PermissionActionListener {

        ProjectScrollPanel scrollPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
            int itemIndex = scrollPanel.getSelectedItemIndex();

            String macAddress = getMacAddressFromList(scrollPanel, itemIndex);

            System.out.println("Giving permission to " + macAddress);

            Set<String> knownDevices = SQLiteDB.getPermittedDevices();

            if (!knownDevices.contains(macAddress)) {
                SQLiteDB.giveDevicePermission(macAddress);
            }

            try {
                ScrollPanelUtilties.updateUnknownDeviceScrollPanel(scrollPanel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        public void setScrollPanel(ProjectScrollPanel scrollPanel) {
            this.scrollPanel = scrollPanel;
        }

        public ProjectScrollPanel getScrollPane() {
            return scrollPanel;
        }
    }

    public static class RemovePermissionActionListener implements PermissionActionListener {

        ProjectScrollPanel scrollPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
            int itemIndex = scrollPanel.getSelectedItemIndex();

            String macAddress = getMacAddressFromList(scrollPanel, itemIndex);

            System.out.println("Removing permission from " + macAddress);

            Set<String> knownDevices = SQLiteDB.getPermittedDevices();

            if (knownDevices.contains(macAddress)) {
                System.out.println("The Mac Address was present");
                SQLiteDB.removeDevicePermission(macAddress);
                
                try {
                    ScrollPanelUtilties.updateKnownDeviceScrollPanel(scrollPanel);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }


        }

        public void setScrollPanel(ProjectScrollPanel scrollPanel) {
            this.scrollPanel = scrollPanel;
        }

        public ProjectScrollPanel getScrollPane() {
            return scrollPanel;
        }
    }

    private static String getMacAddressFromList(ProjectScrollPanel scrollPanel, int index) {
        String result = (String) scrollPanel.getMacAddressJList().getModel().getElementAt(index);

        return result;
    }

    private static List<String> getNewMacAddressList(ProjectScrollPanel scrollPanel, int index) {
        List<String> macAddressList = new ArrayList<>();

        for (int i = 0; i < scrollPanel.getMacAddressList().size(); i++) {
            if (i != index) {
                macAddressList.add((String) scrollPanel.getMacAddressJList().getModel().getElementAt(i));
            }
        }

        return macAddressList;
    }
}
