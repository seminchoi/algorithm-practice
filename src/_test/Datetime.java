package _test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Datetime {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.parse("2022-09-12T10:30:00.142");
        DayOfWeek day = localDateTime.getDayOfWeek();
        System.out.println("day = " + day);
        System.out.println(localDateTime);
    }
}
