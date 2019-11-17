package com.company.ui.controller;

import com.company.ui.view.ProjectScrollPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProjectActionListeners {

    public static DenyPermissionActionListener getDenyPermissionActionListener() {
        return new DenyPermissionActionListener();
    }

    public static PermitPermissionActionListener getPermitPermissionActionListener() {
        return new PermitPermissionActionListener();
    }

    public interface PermissionActionListener extends ActionListener {

        void actionPerformed(ActionEvent e);

        void setScrollPanel(ProjectScrollPanel scrollPanel);

        ProjectScrollPanel getScrollPane();
    }

    public static class DenyPermissionActionListener implements PermissionActionListener {

        ProjectScrollPanel scrollPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Deny " + e.getActionCommand() + " " + scrollPanel.getSelectedItemIndex());
            getMacAddressFromList(scrollPanel, scrollPanel.getSelectedItemIndex());

            List<String> newMacAddressList = getNewMacAddressList(scrollPanel, scrollPanel.getSelectedItemIndex());

            scrollPanel.updateMacAddresses(newMacAddressList);
        }

        public void setScrollPanel(ProjectScrollPanel scrollPanel) {
            this.scrollPanel = scrollPanel;
        }

        public ProjectScrollPanel getScrollPane() {
            return scrollPanel;
        }
    }

    public static class PermitPermissionActionListener implements PermissionActionListener {

        ProjectScrollPanel scrollPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Permit " + e.getActionCommand() + " " + scrollPanel.getSelectedItemIndex());
            getMacAddressFromList(scrollPanel, scrollPanel.getSelectedItemIndex());
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

        System.out.println(result);

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
