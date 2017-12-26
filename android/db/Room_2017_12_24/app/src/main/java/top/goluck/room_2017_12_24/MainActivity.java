package top.goluck.room_2017_12_24;

import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.Date;
import java.util.List;
import top.goluck.room_2017_12_24.db.AppDatabase;
import top.goluck.room_2017_12_24.db.DBUtil;
import top.goluck.room_2017_12_24.model.Book;
import top.goluck.room_2017_12_24.model.Loan;
import top.goluck.room_2017_12_24.model.User;
import top.goluck.room_2017_12_24.util.DateUtil;

/**
 * @author luck
 * 增删改相关示例
 */
public class MainActivity extends AppCompatActivity {

    private TextView txt1, txt2, txt3, txt4;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        db = AppDatabase.getInMemoryDatabase(this);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                addData();
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                selectData();
            }
        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                updateData();
            }
        });
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                deleteData();
            }
        });

        addData();
        selectData();
        updateData();
        deleteData();
    }

    //添加数据
    private void addData() {

        User u1 = DBUtil.addUser(db, "1", "luck", "辣克", 18);
        String data = "添加用户信息：" + u1.toString() + "完成\n";

        Book b1 = DBUtil.addBook(db, "1", "辣克自传");
        data += "添加书籍信息：" + b1.toString() + "完成\n";

        Date twoDaysAgo = DateUtil.getTodayPlusDays(-2);
        Date toDaysAgo = DateUtil.getTodayPlusDays(360);

        Loan l1 = DBUtil.addLoan(db, "1", u1, b1, twoDaysAgo, toDaysAgo);
        data += "添加借书信息：" + l1.toString() + "完成\n";

        txt1.setText(data);
    }

    private void selectData() {

        List<User> users = db.userModel().loadAllUsers();
        String data = "查询用户信息：" + users.toString() + "\n";

        List<Book> books = db.bookModel().findAllBooksSync();
        data += "查询书籍信息：" + books.toString() + "\n";

        List<Loan> loans = db.loanModel().loadAllLoans();
        data += "查询借书信息：" + loans.toString() + "\n";

        //以下查询操作需要异步
        db.loanModel().findAllLoans().observeForever(new Observer<List<Loan>>() {
            @Override public void onChanged(@Nullable List<Loan> loans) {
                Log.i("Room", "------------------------------" + loans.size());
            }
        });
        txt2.setTextColor(Color.GREEN);
        txt2.setText(data);
    }

    private void updateData() {
        List<User> user = db.userModel().loadAllUsers();//查询

        User user1 = user.get(0);
        user1.lastName = "超人";//修改数据
        db.userModel().insertOrReplaceUsers(user1);//执行数据库修改

        List<User> users = db.userModel().loadAllUsers();//查询

        String data = "修改后的用户信息：" + users.toString() + "\n";

        txt3.setTextColor(Color.BLUE);
        txt3.setText(data);
    }

    private void deleteData() {
        //db.loanModel().deleteAll();

        String data = "删除借书信息：完成\n";

        List<Loan> loans = db.loanModel().loadAllLoans();//查询

        if (loans != null && loans.size() > 0) {
            db.loanModel().deleteWhereUserId(loans.get(0).userId);
        }
        txt4.setText(data);
    }
}
