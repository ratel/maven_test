import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.07.13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
public class MainApp {
    final static Logger Log= Logger.getLogger(MainApp.class);

    public static void main(String [] args) {

        try {
        Class cls= Class.forName("org.h2.Driver");
        }
        catch (Exception e) {
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");
            Statement statement = null;
            statement = connection.createStatement();
            String requests = "CREATE TABLE tableTest (ID int, postDate int, postMessage varchar(20));";
            statement.executeUpdate(requests);

            requests = "INSERT INTO tableTest VALUES ('1', '45', 'Description 1');";
            addRecordInBase(requests);
            requests = "INSERT INTO tableTest VALUES ('2', '55', 'Description №2');";
            addRecordInBase(requests);
            requests = "INSERT INTO tableTest VALUES ('3', '65', 'Description #3');";
            addRecordInBase(requests);
            requests = "INSERT INTO tableTest VALUES ('4', '75', 'Description 04');";
            addRecordInBase(requests);
            requests = "INSERT INTO tableTest VALUES ('5', '85', 'Description ___5');";
            addRecordInBase(requests);

            ResultSet resultSet = null;
            resultSet = statement.executeQuery("SELECT ID, postDate, postMessage FROM tableTest");

            System.out.println("ID, postDate, postMessage");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("ID") + "\t" + resultSet.getString("postDate") + "\t" + resultSet.getString("postMessage"));
            }

            resultSet.close();
            statement.close();
            connection.close();

            System.out.println("Программа отработала");

        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    static void addRecordInBase(String requests) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");
        Statement statement = connection.createStatement();
        statement.executeUpdate(requests);

        statement.close();
        connection.close();
    }
}
