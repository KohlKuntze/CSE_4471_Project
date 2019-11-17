package com.company.app;

import com.company.ui.controller.ProjectController;
import com.company.ui.model.ProjectModel;
import com.company.ui.utilities.ScrollPanelUtilties;
import com.company.ui.view.ProjectView;

import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException, InterruptedException {

        ProjectController controller = getController();
        run(controller);

    }

    private static void run(ProjectController controller) throws IOException, InterruptedException {
        while (true) {
            ScrollPanelUtilties.updateView(controller.getProjectView());
            Thread.sleep(5000);

        }
    }


    private static ProjectController getController() {
        ProjectModel model = new ProjectModel("Name");
        ProjectView view = new ProjectView();
        ProjectController controller = new ProjectController(model, view);

        return controller;
    }
}
