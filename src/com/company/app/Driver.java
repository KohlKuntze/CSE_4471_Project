package com.company.app;

import com.company.network.NetworkDevice;
import com.company.ui.controller.ProjectController;
import com.company.ui.model.ProjectModel;
import com.company.ui.utilities.ScrollPanelUtilties;
import com.company.ui.view.ProjectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.company.network.Network.getDevices;

public class Driver {

    public static void main(String[] args) {

        ProjectController controller = getController();
        run(controller);

    }

    private static void run(ProjectController controller) {
        while (true) {
            ScrollPanelUtilties.updateView(controller.getView());
        }
    }


    private static ProjectController getController() {
        ProjectModel model = new ProjectModel("Name");
        ProjectView view = new ProjectView();
        ProjectController controller = new ProjectController(model, view);

        return controller;
    }
}
