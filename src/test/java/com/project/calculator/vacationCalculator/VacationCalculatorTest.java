package com.project.calculator.vacationCalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@SpringBootTest
public class VacationCalculatorTest
{
    private VacationCalculator vacationCalculator;

    @Test
    void sumOfVacation_incorrectDate()
    {
        vacationCalculator=new VacationCalculator(1_000_000, 10, "03:03.25");
        Assertions.assertThrows(DateTimeParseException.class, ()->vacationCalculator.sumOfVacation());
    }

    @Test
    void roundVacationSum() throws Exception
    {
        // РЕФЛЕКСИЯ
        vacationCalculator=new VacationCalculator();
        Method method=VacationCalculator.class.getDeclaredMethod("roundVacationSum", double.class);
        method.setAccessible(true);

        double roundedSum = (double) method.invoke(vacationCalculator, 1000.3786921);

        Assertions.assertEquals(1000.38, roundedSum);
    }

    @Test
    void tryToParseDate_incorrect() throws Exception
    {
        vacationCalculator=new VacationCalculator();
        Method method=VacationCalculator.class.getDeclaredMethod("tryToParseDate", String.class);
        method.setAccessible(true);

        LocalDate parsedDate = (LocalDate) method.invoke(vacationCalculator, "12.11:25");

        Assertions.assertEquals(null, parsedDate);
    }

    @Test
    void sumOfVacation_8March()
    {
        // 8 марта 2025 года выпадает на выходной день, следовательно, он переносится на ближайший будний день
        // следовательно, из этого 10 дневного отпуска вычитаются 2 выходных дня и перенесенное 8 марта. Отпускных - 7 дней

        vacationCalculator=new VacationCalculator(3650, 10, "03.03.2025");
        Assertions.assertEquals(70.0, vacationCalculator.sumOfVacation());
    }

    @Test
    void sumOfVacation_NewYearHolidays()
    {
        // отпуск с 1 января, следовательно, вся неделя приходится на праздничные дни и эти 7 дней вычитаются из отпускных
        // остается 3 отпускны дня

        vacationCalculator=new VacationCalculator(3650, 10, "01.01.2024");
        Assertions.assertEquals(30.0, vacationCalculator.sumOfVacation());
    }

    @Test
    void sumOfVacation_23Feb()
    {
        // 23 февраля 2024 приходится на пяницу, а затем идут 2 выходных дня
        // следовательно, из отпускных вычитаются 3 дня

        vacationCalculator=new VacationCalculator(3650, 10, "19.02.2024");
        Assertions.assertEquals(70.0, vacationCalculator.sumOfVacation());
    }

    @Test
    void sumOfVacation_withoutHolidays()
    {
        // 20-дневный отпуск с 15 июля 2024 года, на этот период приходится 5 выходных
        // следовательно, остается 15 отпускных

        vacationCalculator=new VacationCalculator(1_000_000, 20, "15.07.2024");
        Assertions.assertEquals(41095.89, vacationCalculator.sumOfVacation());
    }
}
