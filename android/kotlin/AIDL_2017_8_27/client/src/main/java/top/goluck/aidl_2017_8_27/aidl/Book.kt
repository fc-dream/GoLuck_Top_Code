package top.goluck.aidl_2017_8_27.aidl

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by luck on 2017/8/27.
 */
data class Book(var name:String,var price:Float): Parcelable {

    constructor():this("", 0f)
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readFloat())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.name)
        parcel.writeFloat(this.price)
    }

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param dest
     */
    fun readFromParcel(dest: Parcel) {
        name = dest.readString()
        price = dest.readFloat()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "name : $name , price : $price"
    }


}
