import controller.ProjectController;
import model.Project;

import java.util.Date;


public class Main {
    public static void main(String[] args){

        ProjectController projectController = new ProjectController();

        Project project = new Project();
        project.setName("Teste projeto");
        project.setDescription("Criação de db teste");
        project.setCreatedAt(new Date());
        project.setUpdatedAt(new Date());

        projectController.save(project);

    }
}
