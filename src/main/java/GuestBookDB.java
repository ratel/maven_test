import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.07.13
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class GuestBookDB implements GuestBookController, AutoCloseable {
    private Connection connection = null;
    private Statement statement = null;
    private String tableName;

    GuestBookDB(String tableName) throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");
        statement = connection.createStatement();
        this.tableName= tableName;
        createTable(tableName);
    }

    @Override
    public void addRecord(String record) throws SQLException {
        String requests;
        SimpleDateFormat dateFormat;
        dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d= new Date(System.currentTimeMillis());

        requests = "INSERT INTO " + tableName + " (postDate, postMessage ) VALUES ('" + dateFormat.format(d) + "', '" + record + "');";
        statement.executeUpdate(requests);
    }

    @Override
    public List<Record> getRecords() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT ID, postDate, postMessage FROM " + tableName);
        List<Record> rez= new ArrayList<>();

        while (resultSet.next()) {
            rez.add(new Record(resultSet.getInt("ID"), resultSet.getString("postDate"), resultSet.getString("postMessage")));
        }

        resultSet.close();

        return rez;
    }

    void createTable(String tableName) throws SQLException {
        String requests = "CREATE TABLE " + tableName + " (ID int AUTO_INCREMENT, postDate DATETIME, postMessage varchar(50), PRIMARY KEY (ID));";

        try {
            statement.executeUpdate(requests);
        } catch(SQLException e) {
            System.err.println("Не удалось создать таблицу для записи!");
            throw e;
        }
    }

    @Override
    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
