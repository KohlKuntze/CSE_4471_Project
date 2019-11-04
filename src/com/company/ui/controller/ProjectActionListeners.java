package com.company.ui.controller;

import com.company.ui.view.ProjectScrollPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectActionListeners {

    public static PermissionActionListener getDenyPermissionActionListener() {
        return new PermissionActionListener();
    }

    public static class PermissionActionListener implements ActionListener {

        ProjectScrollPanel scrollPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand() + " " + scrollPanel.getSelectedItemIndex());
        }

        public void setScrollPanel(ProjectScrollPanel scrollPanel) {
            this.scrollPanel = scrollPanel;
        }
    }
}
