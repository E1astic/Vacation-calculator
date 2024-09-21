package com.project.calculator.holidays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class HolidayCalendarTest
{
    private final int currentYear=2024;
    private HolidayCalendar calendar;

    @BeforeEach
    void setUp()
    {
        calendar=new HolidayCalendar(currentYear);
    }

    @Test
    void isWeekend_Saturday()
    {
        LocalDate date=LocalDate.of(currentYear,9,21);
        Assertions.assertEquals(true, calendar.isWeekend(date));
    }

    @Test
    void isWeekend_Sunday()
    {
        LocalDate date=LocalDate.of(currentYear,9,23);
        Assertions.assertEquals(false, calendar.isWeekend(date));
    }

    @Test
    void isHoliday_23Feb()
    {
        LocalDate date=LocalDate.of(currentYear,2,23);
        Assertions.assertEquals(true, calendar.isHoliday(date));
    }

    @Test
    void isHoliday_25Feb()
    {
        LocalDate date=LocalDate.of(currentYear,2,25);
        Assertions.assertEquals(false, calendar.isHoliday(date));
    }

    @Test
    void isNewYearHolidays_5Jan()
    {
        LocalDate date=LocalDate.of(currentYear,1,5);
        Assertions.assertEquals(true, calendar.isNewYearHolidays(date));
    }

    @Test
    void isNewYearHolidays_10Jan()
    {
        LocalDate date=LocalDate.of(currentYear,1,10);
        Assertions.assertEquals(false, calendar.isNewYearHolidays(date));
    }
}
