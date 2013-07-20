/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.07.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
public class Record {
    int id;
    int PostDate;
    String message;

    Record(int id, int PostDate, String message) {
        this.id= id;
        this.PostDate= PostDate;
        this.message= message;
    }
}
