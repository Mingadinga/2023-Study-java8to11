package me.whiteship.practice.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextMonthFirstWeekdayAdjuster implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate date = LocalDate.from(temporal);

        // 다음 달의 첫 번째 날짜를 구함
        LocalDate nextMonthFirstDay = date.plusMonths(1).withDayOfMonth(1);

        // 다음 달의 첫 번째 날짜부터 첫 번째 평일(월~금)을 구함
        LocalDate nextMonthFirstWeekday = nextMonthFirstDay.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

        return nextMonthFirstWeekday;
    }
}

