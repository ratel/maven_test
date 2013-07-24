import org.apache.log4j.Logger;

import java.sql.SQLException;

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
        Class.forName("org.h2.Driver");
        }
        catch (Exception e) {
        }

        ManagerGuestBook mgBook= null;

        try {
            mgBook= new ManagerGuestBook(System.in, System.out, System.err);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (mgBook != null)
            mgBook.work();

    }
}
