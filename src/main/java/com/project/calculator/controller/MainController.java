package com.project.calculator.controller;

import com.project.calculator.vacationCalculator.VacationCalculator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeParseException;

@RestController
public class MainController
{

    @GetMapping("/calculate")
    public ResponseEntity<?> sumOfVacation(@RequestParam int averageSalaryPerYear, @RequestParam int countVacationDays, @RequestParam String date)
    {
        if(averageSalaryPerYear<=0 || countVacationDays<=0)
            return ResponseEntity.badRequest().body("Зарплата или количество отпускных не могут быть меньше, либо равны нулю.");

        double sum=0.0;

        try
        {
            VacationCalculator calculator = new VacationCalculator(averageSalaryPerYear, countVacationDays, date);
            sum = calculator.sumOfVacation();
        }
        catch(DateTimeParseException e)
        {
            return ResponseEntity.badRequest().body("Неверный формат даты.\nПожалуйста, введите один из следующих форматов\n" +
                    "dd.mm.yyyy\ndd-mm-yyyy\ndd:mm:yyyy\ndd/mm/yyyy\n");
        }

        return ResponseEntity.ok(sum);
    }

}
