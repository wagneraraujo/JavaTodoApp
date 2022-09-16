package controller;

import model.Task;
import util.ConnectionFactory;

import java.sql.*;
import java.util.List;

public class taskController
{

    public void save(Task task){

        String sql = "INSERT INTO tasks (idProject" +
                "name," +
                "description," +
                "completed," +
                "notes," +
                "createdAt," +
                "updateAt," +
                ") VALUES (?,?,?,?,?,?,?,?,?)";
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
            ConnectionFactory.closeConnection(connection);
        }
    }

    public void update(Task task){}

    public void removeById(int taskId) throws SQLDataException {
        String sql = "DELETE from tasks WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;

        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error delete task" + e);
        }
        finally {
            ConnectionFactory.closeConnection(conn);
        }

    }

    public List<Task> getAll(int idProject){
        return null;
    }
}
