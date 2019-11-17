package com.company.ui.view;

import com.company.ui.controller.ProjectActionListeners.PermissionActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ProjectScrollPanel extends JPanel {

    DefaultListModel model;
    final JButton actionButton;
    final JList macAddressJList;
    JScrollPane scrollPane;
    SelectedItemListener selectedItemListener;

    public ProjectScrollPanel(PermissionActionListener actionListener, String label) {
        setLayout(new BorderLayout());

        actionButton = getActionButton(label, actionListener);
        add(actionButton, BorderLayout.NORTH);

        model = createMacAddressListModel(new ArrayList<>());

        macAddressJList = new JList();
        macAddressJList.setModel(model);

        selectedItemListener = new SelectedItemListener();
        macAddressJList.addListSelectionListener(selectedItemListener);

        scrollPane = getScrollPane(macAddressJList);
        add(scrollPane);

        actionListener.setScrollPanel(this);

        setVisible(true);
    }

    public DefaultListModel getModel() {
        return model;
    }

    public JList getMacAddressJList() {
        return macAddressJList;
    }

    public List<String> getMacAddressList() {
        List<String> macAddressList = new ArrayList<>();

        ListModel listModel = macAddressJList.getModel();

        for (int i = 0; i < listModel.getSize(); i++) {
            Object currentObject = listModel.getElementAt(i);
            String currentMacAddress = (String) currentObject;

            macAddressList.add(currentMacAddress);
        }

        return macAddressList;
    }

    public void updateMacAddresses(List<String> updatedMacAddresses) {
        model = createMacAddressListModel(updatedMacAddresses);
        macAddressJList.setModel(model);
    }

    public int getSelectedItemIndex() {
        return selectedItemListener.selectedItemIndex;
    }

    private static JScrollPane getScrollPane(JList macAddressJList) {
        JScrollPane scrollPane = new JScrollPane(macAddressJList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(750, 500);

        return scrollPane;
    }

    private static JButton getActionButton(String label, PermissionActionListener actionListener) {
        JButton actionButton = new JButton(label);

        actionButton.addActionListener(actionListener);
        actionButton.setSize(750, 50);
        actionButton.setVisible(true);

        return actionButton;
    }

    private static DefaultListModel createMacAddressListModel(List<String> macAddresses) {
        DefaultListModel model = new DefaultListModel();

        for (int i = 0; i < macAddresses.size(); i++) {
            String currentMacAddress = macAddresses.get(i);

            model.addElement(currentMacAddress);
        }

        return model;
    }

    private class SelectedItemListener implements ListSelectionListener {

        Integer selectedItemIndex;

        public SelectedItemListener() {
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                if (selectedItemIndex == null) {
                    selectedItemIndex = e.getLastIndex();
                } else {
                    if (selectedItemIndex == e.getFirstIndex()) {
                        selectedItemIndex = e.getLastIndex();
                    } else {
                        selectedItemIndex = e.getFirstIndex();
                    }
                }
            }
        }


    }
}
