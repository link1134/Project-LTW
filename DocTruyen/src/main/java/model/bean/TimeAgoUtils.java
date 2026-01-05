package model.bean;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeAgoUtils {
	public static String format(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);
        
        long seconds = duration.getSeconds();
        
        if (seconds < 60) return seconds + " giây trước";
        
        long minutes = duration.toMinutes();
        if (minutes < 60) return minutes + " phút trước";
        
        long hours = duration.toHours();
        if (hours < 24) return hours + " giờ trước";
        
        long days = ChronoUnit.DAYS.between(dateTime, now);
        if (days < 30) return days + " ngày trước";
        
        long months = ChronoUnit.MONTHS.between(dateTime, now);
        if (months < 12) return months + " tháng trước";
        
        long years = ChronoUnit.YEARS.between(dateTime, now);
        return years + " năm trước";
    }
}
