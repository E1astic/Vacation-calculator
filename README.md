﻿# VacationCalculator
Приложение "Калькулятор отпускных".
Микросервис на SpringBoot + Java 11 c одним API: GET "/calculacte".

Приложение принимает среднюю зарплату за 12 месяцев, количество дней отпуска и дату ухода в отпуск. Возвращает сумму отпускных. Приложение производит расчет с учетом праздничных и выходных дней (если праздник пришелся на выходной день, то он, соответственно, переносится на ближайший будний день и не идет в расчет отпускных).

Программа имеет следующие классы:
1) CalculatorApplication - содержит метод main.
2) MainController - класс, в котором обработан единственный запрос GET "/calculate".
3) HolidayCalendar - содержит информацию о главных праздниках за конкретный год, переданный в параметр конструктора. Выполняет проверку конкретного дня на факт выходного, праздника и отдельно на новогодние каникулы.
4) VacationCalculator - класс содержит метод расчета отпускных и вспомогательные приватные методы по округлению результата до 2 знаков после запятой, а также обработкой формата введенной даты.

Программа обрабатывает ситуации, если введены неположительные значения зарплаты или количества дней отпуска, а также случаи некорректного формата введенной даты.

Приложение содержит 2 класса с юнит-тестами: HolidayCalendarTest и VacationCalculatorTest.
