package GuestBook;

import org.apache.log4j.Logger;

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
            System.err.println("Неудалось загрузить драйвер базы данных! Работа невозможна!");
            return;
        }

        ManagerGuestBook mgBook= new ManagerGuestBook(System.in, System.out, System.err);
        mgBook.work();
    }
}                   // посмотреть http://javatalks.ru/topics/22841
                    // http://javakyiv.blogspot.ru/2012/10/java-c-idea-tomcat.html#!/2012/10/java-c-idea-tomcat.html
