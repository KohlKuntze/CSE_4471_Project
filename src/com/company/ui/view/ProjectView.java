package com.company.ui.view;

import com.company.ui.controller.ProjectActionListeners;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectView extends JFrame {

    private static final Integer OVERALL_WIDTH = 1500;
    private static final Integer OVERALL_HEIGHT = 750;

    private final ProjectScrollPanel unknownIpAddressesPanel;
    private final ProjectScrollPanel knownIpAddressesPanel;

    public ProjectView() {
        super("Network Monitor");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(OVERALL_WIDTH, OVERALL_HEIGHT);
        this.setLayout(new GridLayout(1, 2));

        unknownIpAddressesPanel = new ProjectScrollPanel(ProjectActionListeners.getDenyPermissionActionListener(), "Remove");
        this.add(unknownIpAddressesPanel);

        knownIpAddressesPanel = new ProjectScrollPanel(ProjectActionListeners.getPermitPermissionActionListener(), "Add");
        this.add(knownIpAddressesPanel);

        this.setVisible(true);
    }


    public static ProjectView getInstance() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        ProjectView newView = new ProjectView();
        return newView;
    }
}
