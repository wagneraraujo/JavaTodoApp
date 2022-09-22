package controller;

import model.Project;
import util.ConnectionFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    public void save(Project project){
        String sql = "INSERT INTO projects (name," +
                "description," +
                "createdAt," +
                "updateAt)" +
                "VALUES (?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try{

            //create connection with the base data
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error connection insert Project" + e);
        }
        finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Project project){

        String sql = "UPDATE projects SET name = ?," +
                "description = ? ," +
                "createdAt = ?," +
                "updateAt = ?," +
                "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, (java.sql.Date) new Date( project.getUpdatedAt().getTime()));
            statement.setDate(4, (java.sql.Date) new Date( project.getCreatedAt().getTime()));

            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Error update",e);
        }

    }

    public List<Project> getAll(){

        String sql = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet resultSet = null;

        try{

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();




            while (resultSet.next()){
                Project project =new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription((resultSet.getString("description")));

                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updateAt"));

                projects.add(project);

            }
        } catch (SQLException e) {
            throw new RuntimeException("error select all projects "+e);
        }

        return  projects;

    }


    public void  removeById(int idProject){
        String sql = "DELETE FROM projects WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

}
