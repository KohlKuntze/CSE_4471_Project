package com.company.ui.controller;

import com.company.ui.view.ProjectScrollPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectActionListeners {

    public static PermissionActionListener getDenyPermissionActionListener() {
        return new DenyPermissionActionListener();
    }

    public static PermissionActionListener getPermitPermissionActionListener() {
        return new PermitPermissionActionListener();
    }

    public interface PermissionActionListener extends ActionListener {

        @Override
        public void actionPerformed(ActionEvent e);

        public void setScrollPanel(ProjectScrollPanel scrollPanel);

        public ProjectScrollPanel getScrollPane();
    }

    public static class DenyPermissionActionListener implements PermissionActionListener {

        ProjectScrollPanel scrollPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand() + " " + scrollPanel.getSelectedItemIndex());
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
            System.out.println(e.getActionCommand() + " " + scrollPanel.getSelectedItemIndex());
        }

        public void setScrollPanel(ProjectScrollPanel scrollPanel) {
            this.scrollPanel = scrollPanel;
        }

        public ProjectScrollPanel getScrollPane() {
            return scrollPanel;
        }
    }
}
