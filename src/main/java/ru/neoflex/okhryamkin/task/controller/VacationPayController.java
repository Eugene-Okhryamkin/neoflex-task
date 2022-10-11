package ru.neoflex.okhryamkin.task.controller;

import org.springframework.web.bind.annotation.*;
import ru.neoflex.okhryamkin.task.service.VacationPayService;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/holidaypay")
public class VacationPayController {

    private VacationPayService holidayPayService;

    public VacationPayController(VacationPayService holidayPayService) {
        this.holidayPayService = holidayPayService;
    }

    @GetMapping(value = "/calculate", params = {"averageSalary", "amountOfHolidays"})
    @ResponseBody
    public double calculateHolidayPay(@RequestParam double averageSalary, @RequestParam int amountOfHolidays) {
        return this.holidayPayService.calculate(averageSalary, amountOfHolidays);
    }


    /*
        @param startDate - start of vacations
        @param endDate - end of vacations
     */
    @GetMapping(value = "/calculate", params = {"averageSalary", "startDate", "endDate"})
    @ResponseBody
    public double calculateHolidayPay(@RequestParam double averageSalary, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return this.holidayPayService.calculate(averageSalary, startDate, endDate);
    }

}
