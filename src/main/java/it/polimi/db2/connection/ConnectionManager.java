package it.polimi.db2.connection;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection tryConnection(ServletContext context) throws ClassNotFoundException, SQLException{
        String driver = context.getInitParameter("dbDriver");
        String url = context.getInitParameter("dbUrl");
        String user = context.getInitParameter("dbUser");
        String password = context.getInitParameter("dbPassword");
        Class.forName(driver);
        return DriverManager.getConnection(url,user,password);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if(connection!=null){
            connection.close();
        }
    }

}
