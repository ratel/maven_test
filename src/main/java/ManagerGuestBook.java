import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Паша
 * Date: 24.07.13
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class ManagerGuestBook {
    private final String ADD_CMD = "add";
    private final String LIST_CMD = "list";

    private BufferedReader inReader;
    private OutputStreamWriter outWriter;
    private OutputStreamWriter errWriter;

    private GuestBookController gBook= null;


    ManagerGuestBook(InputStream in, OutputStream out, OutputStream err) {
        inReader= new BufferedReader(new InputStreamReader(in));
        outWriter= new OutputStreamWriter(out);
        errWriter= new OutputStreamWriter(err);
    }

    private void doCmd(String cmd) {
        if (cmd != null) {
            String [] splitCmd= cmd.split(" ");

            if (ADD_CMD.equalsIgnoreCase(splitCmd[0])) {
                if (splitCmd.length > 1)
                    doAdd(cmd.substring(splitCmd[0].length() + 1));
                else {
                    outErrln("Для исполнения команды \"" + ADD_CMD + "\" требуется ввести текстовую строку в качестве параметра!");
                }
            } else if (LIST_CMD.equalsIgnoreCase(splitCmd[0])) {
                doList();
            } else {
                try {
                    outWriter.write("Неизвестная команда (\"" + cmd + "\")\r\n");
                    outWriter.flush();
                } catch (IOException e) {
                    outErrln("Ошибка при выводе данных!");
                    e.printStackTrace();
                }
            }
        }
    }

    private void doAdd(String addText) {
        try {
            gBook.addRecord(addText);
        } catch (SQLException e) {
            outErrln("Ошибка при добавлении записи!");
            e.printStackTrace();
        }
    }

    private void doList() {
        List<Record> rec;

        try {
            rec= gBook.getRecords();

            for (Record iR : rec) {
                outWriter.write(iR.getId() + "\t" + iR.getPostDate() + "\t" + iR.getMessage() + "\r\n");
            }

            if (rec.size() == 0)
                outWriter.write("Таблица пуста.\r\n");

            outWriter.flush();
        } catch (SQLException e) {
            outErrln("Ошибка при получении списка записей!");
            e.printStackTrace();
        } catch (IOException e) {
            outErrln("Ошибка при выводе данных гостевой книги!");
            e.printStackTrace();
        }
    }

    public void work() {
        final String BREAKWORK= "exit";
        String cmdStr= null;

        try (GuestBookDB gBook= new GuestBookDB("tableTest")) {
            this.gBook= gBook;

            while (!BREAKWORK.equalsIgnoreCase(cmdStr)) {
                try {
                    cmdStr= inReader.readLine();
                    doCmd(cmdStr);
                } catch (IOException e) {
                    outErrln("Ошибка при считывании команды!");
                    e.printStackTrace();
                    break;
                }
            }
        } catch (SQLException e) {
            outErrln("Ошибка при считывании команды!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void outErrln(String errStr) {
        try {
            errWriter.write(errStr + "\r\n");
            errWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
