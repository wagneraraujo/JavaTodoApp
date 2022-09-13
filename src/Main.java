import util.ConnectionFactory;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){

        Connection c = ConnectionFactory.getConnection();

        //close ex
        //ConnectionFactory.closeConnection(c);

    }
}
