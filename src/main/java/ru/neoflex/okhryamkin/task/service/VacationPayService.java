package ru.neoflex.okhryamkin.task.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.*;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class VacationPayService {

    private int calculateAmountOfVacations(LocalDate startDate, LocalDate endDate) {
        int differenceDays = (int) DAYS.between(startDate, endDate);

        //dates actually for
        final Set<LocalDate> holidays = Set.of(
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 1, 2),
                LocalDate.of(2022, 1, 3),
                LocalDate.of(2022, 1, 4),
                LocalDate.of(2022, 1, 5),
                LocalDate.of(2022, 1, 6),
                LocalDate.of(2022, 1, 7),
                LocalDate.of(2022, 1, 8),
                LocalDate.of(2022, 1, 9),
                LocalDate.of(2022, 2, 23),
                LocalDate.of(2022, 3, 8),
                LocalDate.of(2022, 5, 1),
                LocalDate.of(2022, 5, 9),
                LocalDate.of(2022, 6, 12),
                LocalDate.of(2022, 10, 4)
        );

        final Set<DayOfWeek> businessDays = Set.of(
                MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
        );

        int daysWithoutHolidays = (int) startDate.datesUntil(endDate)
                        .filter(t -> businessDays.contains(t.getDayOfWeek()))
                        .filter(t -> !holidays.contains(t)).collect(Collectors.toList()).stream().count();


        return (differenceDays - daysWithoutHolidays) + differenceDays;
    }

    public double calculate(double averageSalary, LocalDate startDate, LocalDate endDate) {
        System.out.println(calculateAmountOfVacations(startDate, endDate));
        return (averageSalary / 29.3) * calculateAmountOfVacations(startDate, endDate);
    }

    public double calculate(double averageSalary, int amountOfHolidays) {
        //29.3 business days in month
        return (averageSalary / 29.3) * amountOfHolidays;
    }
}
