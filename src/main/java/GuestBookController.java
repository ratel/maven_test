import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.07.13
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public interface GuestBookController {
    void addRecord(String record) throws SQLException;
    List<Record> getRecords() throws SQLException;
}
