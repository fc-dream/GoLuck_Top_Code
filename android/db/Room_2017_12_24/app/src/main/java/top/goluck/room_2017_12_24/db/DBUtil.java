package top.goluck.room_2017_12_24.db;

import android.database.sqlite.SQLiteConstraintException;
import java.util.Date;
import top.goluck.room_2017_12_24.model.Book;
import top.goluck.room_2017_12_24.model.Loan;
import top.goluck.room_2017_12_24.model.User;

/**
 * 作者：luck on 2017/12/24 17:27
 * 邮箱：fc_dream@163.com
 * db
 */
public class DBUtil {

    public static Loan addLoan(final AppDatabase db, final String id,
            final User user, final Book book, Date from, Date to) {
        Loan loan = new Loan();
        loan.id = id;
        loan.bookId = book.id;
        loan.userId = user.id;
        loan.startTime = from;
        loan.endTime = to;
        try {
            db.loanModel().insertLoan(loan);
        }catch (SQLiteConstraintException e){
            // id 重复之类的问题
            e.printStackTrace();
        }
        return loan;
    }

    public static Book addBook(final AppDatabase db, final String id, final String title) {
        Book book = new Book();
        book.id = id;
        book.title = title;
        try {
            db.bookModel().insertBook(book);
        }catch (SQLiteConstraintException e){
            // id 重复之类的问题
            e.printStackTrace();
        }
        return book;
    }

    public static User addUser(final AppDatabase db, final String id, final String name,
            final String lastName, final int age) {
        User user = new User();
        user.id = id;
        user.age = age;
        user.name = name;
        user.lastName = lastName;
        try {
            db.userModel().insertUser(user);
        }catch (SQLiteConstraintException e){
            // id 重复之类的问题
            e.printStackTrace();
        }
        return user;
    }

}
