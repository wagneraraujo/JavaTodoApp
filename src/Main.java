import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;

import java.sql.SQLDataException;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLDataException {

//        ProjectController projectController = new ProjectController();
//
//        Project project = new Project();

        //create
//        project.setName("Teste projeto");
//        project.setDescription("Criação de db teste");
//        project.setCreatedAt(new Date());
//        project.setUpdatedAt(new Date());
//        projectController.save(project);

        //update
//        project.setName("atualizar titulo");
//        project.setDescription("update descriptionssss");
//        project.setUpdatedAt(new Date());
//        project.setId(1);
//        projectController.update(project);

//        list projects
//        List<Project> projects = projectController.getAll();
//        System.out.println("Total projects: " + projects.size());

        //remover
//        projectController.removeById(1);


//        create task
        TaskController taskController = new TaskController();
        Task task = new Task();
//        task.setIdProject(2);
//        task.setName("New task again");
//        task.setDescription("test new task");
//        task.setCompleted(false);
//        task.setNotes("test notes");
//        task.setDeadline(new Date());
//        task.setCreatedAt(new Date());
//        task.setUpdateAt(new Date());
//        taskController.save(task);

//  =====       update task
//        task.setIdProject(2);
//        task.setName("New task again rename");
//        task.setDescription("test new task");
//        task.setCompleted(false);
//        task.setNotes("test notes");
//        task.setDeadline(new Date());
//        task.setUpdateAt(new Date());
//        task.setId(1);
//        taskController.update(task);

//        taskController.removeById(1);
      List<Task> tasks = taskController.getAll(2);
      System.out.println("List tasks for projects " + tasks .size());


    }
}
