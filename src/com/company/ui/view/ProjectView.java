package com.company.ui.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectView extends JFrame{

    private static final Integer OVERALL_WIDTH = 1500;
    private static final Integer OVERALL_HEIGHT = 750;

    private static final Integer LIST_WIDTH = 1400;
    private static final Integer LIST_HEIGHT = 325;

    private final JLabel unknownIpAddressesLabel;
    private final JScrollPane unknownIpAddressScrollPane;

    public ProjectView() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(OVERALL_WIDTH, OVERALL_HEIGHT);
        this.setLayout(new FlowLayout());

        unknownIpAddressesLabel = new JLabel("Unknown IP Addresses:");
        this.add(unknownIpAddressesLabel);

        unknownIpAddressScrollPane = createUnknownIpAddressScrollPanePanel();
        this.add(unknownIpAddressScrollPane);

        this.setVisible(true);
    }

    private static JScrollPane createUnknownIpAddressScrollPanePanel() {
        JScrollPane unknownIpAddressScrollPane = new JScrollPane();
        Dimension scrollDimension = new Dimension(95, 375);
        unknownIpAddressScrollPane.setSize(scrollDimension);
        unknownIpAddressScrollPane.createVerticalScrollBar();
        unknownIpAddressScrollPane.setWheelScrollingEnabled(true);

        return unknownIpAddressScrollPane;
    }


    public static ProjectView getInstance() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        ProjectView newView = new ProjectView();
        return newView;
    }
}
