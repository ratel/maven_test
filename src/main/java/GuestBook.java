import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.07.13
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class GuestBook implements GuestBookController {
    private Connection connection = null;
    private Statement statement = null;
    private String tableName;

    GuestBook() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");
        statement = connection.createStatement();
    }
    @Override
    public void addRecord(String record) throws SQLException {
        String requests;
        requests = "INSERT INTO " + tableName + "VALUES ('5', '85', '" + record + "');";

        statement.executeUpdate(requests);

//        statement.close();
//        connection.close();
    }

    @Override
    public List<Record> getRecord() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT ID, postDate, postMessage FROM " + tableName);
        List<Record> rez= new ArrayList<>();

        System.out.println("ID, postDate, postMessage");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("ID") + "\t" + resultSet.getString("postDate") + "\t" + resultSet.getString("postMessage"));
            rez.add(new Record(resultSet.getInt("ID"), resultSet.getInt("postDate"), resultSet.getString("postMessage")));
        }

        resultSet.close();

        return rez;
    }
}
