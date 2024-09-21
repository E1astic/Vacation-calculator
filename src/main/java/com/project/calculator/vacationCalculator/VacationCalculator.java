package com.project.calculator.vacationCalculator;

import com.project.calculator.holidays.HolidayCalendar;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationCalculator
{
    private int averageSalaryPerYear;
    private int countVacationDays;
    private String dateOfStartingVacation;
    private static final int countDaysPerYear = 365;



    public double sumOfVacation()
    {
        double salaryPerDay = (double)averageSalaryPerYear / countDaysPerYear;

        LocalDate startOfHolidays = tryToParseDate(dateOfStartingVacation);

        if(startOfHolidays == null)
            throw new DateTimeParseException("Неверный формат введенной даты.", dateOfStartingVacation, 0);

        LocalDate endOfHolidays = startOfHolidays.plusDays(countVacationDays);

        HolidayCalendar holidayCalendar = new HolidayCalendar(startOfHolidays.getYear());

        for(LocalDate day = startOfHolidays; day.isBefore(endOfHolidays); day = day.plusDays(1))
        {
            if(day.getYear()>day.minusDays(1).getYear())  // если наступил новый год, то создаем новый календарь праздников
                holidayCalendar =new HolidayCalendar(day.getYear());


            if(holidayCalendar.isNewYearHolidays(day))
                --countVacationDays;
            else if(holidayCalendar.isHoliday(day) && holidayCalendar.isWeekend(day))  // если праздник пришелся на выходной день, то он переносится на ближайший будний день
                countVacationDays-=2;
            else if(holidayCalendar.isWeekend(day) || holidayCalendar.isHoliday(day))
                --countVacationDays;


            if(countVacationDays<0)
                return 0;
        }

        double sum = salaryPerDay * countVacationDays;

        sum = roundVacationSum(sum);

        return sum;
    }


    private double roundVacationSum(double sum)
    {
        String formattedSum=String.format("%.2f", sum);
        formattedSum = formattedSum.replace(",", ".");
        double res=Double.parseDouble(formattedSum);
        return res;
    }


    private LocalDate tryToParseDate(String dateString)
    {
        DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatter3=DateTimeFormatter.ofPattern("dd:MM:yyyy");
        DateTimeFormatter formatter4=DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date=null;

        try {
            date=LocalDate.parse(dateString, formatter1);
        }
        catch(DateTimeParseException e1){
            try {
                date = LocalDate.parse(dateString, formatter2);
            }
            catch(DateTimeParseException e2){
                try {
                    date = LocalDate.parse(dateString, formatter3);
                }
                catch(DateTimeParseException e3){
                    try {
                        date = LocalDate.parse(dateString, formatter4);
                    }
                    catch(DateTimeParseException e4){
                        return null;
                    }
                }
            }
        }

        return date;
    }

}
