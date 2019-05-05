package vn.edu.hcmut.core.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
	public static String dateTimeToString() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public static String dateTimeToString(String stringDate) {
		String date = "";
		String[] date1 = stringDate.split(" ");
		String[] date2 = date1[0].split("/");
		date = date2[2] + "-" + date2[1] + "-" + date2[0] + " " + date1[1] + ":00";
		return date;
	}

	public static Date stringToDatetime(String stringDate) {
		Locale locale = new Locale("vi", "VN");
		String pattern = "dd/MM/yyyy hh:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date;
		try {
			date = simpleDateFormat.parse(stringDate);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
