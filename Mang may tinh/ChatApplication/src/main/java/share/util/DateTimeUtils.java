package share.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {

    //Tính khoảng cách từ ngày hiện tại đến ngày truyền vào
    public static String getDiffDate(Timestamp timestamp) {
        Date date1 = new Date();
        Date date2 = new Date(timestamp.getTime());
        long getDiff = date1.getTime() - date2.getTime();
        long daysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);
        if (daysDiff > 0)
            return daysDiff + " ngày";
        long hoursDiff = TimeUnit.MILLISECONDS.toHours(getDiff);
        if (hoursDiff > 0)
            return hoursDiff + " giờ";
        long minutesDiff = TimeUnit.MILLISECONDS.toMinutes(getDiff);
        if (minutesDiff > 0)
            return minutesDiff + " phút";
        long secondsDiff = TimeUnit.MILLISECONDS.toSeconds(getDiff);
        if (secondsDiff > 0)
            return secondsDiff + " giây";
        return "";
    }
}
