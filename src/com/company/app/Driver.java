package com.company.app;

import com.company.ui.controller.ProjectController;
import com.company.ui.model.ProjectModel;
import com.company.ui.utilities.ScrollPanelUtilties;
import com.company.ui.view.ProjectView;

public class Driver {

    public static void main(String[] args) {

        ProjectController controller = getController();
        run(controller);

    }

    private static void run(ProjectController controller) {
        while (true) {
            ScrollPanelUtilties.updateView(controller.getProjectView());
        }
    }


    private static ProjectController getController() {
        ProjectModel model = new ProjectModel("Name");
        ProjectView view = new ProjectView();
        ProjectController controller = new ProjectController(model, view);

        return controller;
    }
}
