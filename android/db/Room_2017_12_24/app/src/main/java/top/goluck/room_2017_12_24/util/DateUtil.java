package top.goluck.room_2017_12_24.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 作者：luck on 2017/12/24 17:30
 * 邮箱：fc_dream@163.com
 * db
 */
public class DateUtil {

    public static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }

}
