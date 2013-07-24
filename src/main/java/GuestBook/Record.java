package GuestBook;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.07.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
public class Record {
    private int id;
    private String postDate;
    private String message;

    Record(int id, String postDate, String message) {
        this.id= id;
        this.postDate= postDate;
        this.message= message;
    }

    int getId() {
        return id;
    }

    String getPostDate() {
        return postDate;
    }

    String getMessage() {
        return message;
    }

}
