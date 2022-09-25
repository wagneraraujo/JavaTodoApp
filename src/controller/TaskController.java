package controller;

import model.Task;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    public void save(Task task){
        String sql = "INSERT INTO tasks (idProject,name,description,completed,notes,deadline,createdAt,updateAt) VALUES (?,?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2,task.getName());
            statement.setString(3,task.getDescription());
            statement.setBoolean(4,task.isCompleted());
            statement.setString(5,task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8,new Date(task.getUpdateAt().getTime()));

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("error create task" + e);
        }
        finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    public void update(Task task){
        String sql = "UPDATE tasks SET idProject =?,name = ?,description = ?,notes = ?,completed = ? ,deadline = ? ,updateAt = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5,task.isCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getUpdateAt().getTime()));
            statement.setInt(8, task.getId());
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Error in update"+ e);
        }
    }

    public void removeById(int taskId){
        String sql = "DELETE from tasks WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try{
            //create connection
            conn = ConnectionFactory.getConnection();
            //create query
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (SQLException e) {
            //runtimeException is for any kind of exception
            throw new RuntimeException("Error delete task" + e);
        }
        finally {
            //whenever I am open, I  have to close: connection and statement
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public List<Task> getAll(int idProject){
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        // return result DB with values
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<Task>();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,idProject);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Task task = new Task();
                //set values that are inside the resultSet
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdateAt(resultSet.getDate("updateAt"));
                //insert list inside this task
                tasks.add(task);

            }
        }
        catch (Exception e){
            throw new RuntimeException("error get tasks" + e);
        }
        finally {
            ConnectionFactory.closeConnection(connection,statement, resultSet);
        }


        //list tasks created
        return tasks;
    }
}
