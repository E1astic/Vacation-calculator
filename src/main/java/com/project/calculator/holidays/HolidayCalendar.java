package com.project.calculator.holidays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class HolidayCalendar
{
    private int currentYear;

    private Set<LocalDate> holidays;
    private LocalDate startNewYearHolidays;
    private LocalDate endNewYearHolidays;

    public HolidayCalendar(int currentYear)
    {
        this.currentYear=currentYear;

        holidays = new HashSet<>()
        {{
            add(LocalDate.of(currentYear, 6,12));  // День России
            add(LocalDate.of(currentYear, 3,8));   // 8 Марта
            add(LocalDate.of(currentYear, 5,1));   // 1 Мая
            add(LocalDate.of(currentYear, 5,9));   // 9 Мая
            add(LocalDate.of(currentYear, 2,23));  // 23 Февраля
        }};

        startNewYearHolidays=LocalDate.of(currentYear, 12, 31);  // Новогодние каникулы
        endNewYearHolidays=LocalDate.of(currentYear, 1, 7);
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public boolean isWeekend(LocalDate date)
    {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isNewYearHolidays(LocalDate date)
    {
        if(date.isEqual(startNewYearHolidays))
            return true;
        if(date.isAfter(startNewYearHolidays.minusYears(1)) && !date.isAfter(endNewYearHolidays))
            return true;
        return false;
    }

    public boolean isHoliday(LocalDate date)
    {
        if(holidays.contains(date))
            return true;
        return false;
    }


}
