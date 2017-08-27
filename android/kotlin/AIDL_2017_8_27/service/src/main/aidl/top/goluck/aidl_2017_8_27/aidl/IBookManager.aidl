// IBookManager.aidl
package top.goluck.aidl_2017_8_27.aidl;
import top.goluck.aidl_2017_8_27.aidl.Book;

interface IBookManager {

    void sendBook(in Book book);

    void udpateBook(out Book book);

    String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

}
