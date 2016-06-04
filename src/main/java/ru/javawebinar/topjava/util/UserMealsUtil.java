package main.java.ru.javawebinar.topjava.util;

import com.sun.javafx.collections.MapAdapterChange;
import main.java.ru.javawebinar.topjava.model.UserMeal;
import main.java.ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01, 8, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01, 20, 0), "Ужин", 510)
        );
        for (UserMealWithExceed um : getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000))
            System.out.println(um.toString());
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        /*Реализовать UserMealsUtil.getFilteredMealsWithExceeded:
        -  должны возвращаться только записи между startTime и endTime
                -  поле UserMealWithExceed.exceed должно показывать,
                превышает ли сумма калорий за весь день параметра метода caloriesPerDay
        //HW0
        Т.е UserMealWithExceed - это запись одной еды, но поле exceeded будет одинаково для всех записей за этот день.*/
        List<UserMealWithExceed> me = new ArrayList<>();
        Map<LocalDate, Integer> calperday = new HashMap<>();
        Integer cal;
        for (UserMeal um : mealList) {
            cal = calperday.get(um.getDateTime().toLocalDate());
            if (cal == null)
                calperday.put(um.getDateTime().toLocalDate(), um.getCalories());
            else
                calperday.put(um.getDateTime().toLocalDate(), cal + um.getCalories());
        }

        for (UserMeal um : mealList)
            if (calperday.get(um.getDateTime().toLocalDate()) > caloriesPerDay
                    && TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                me.add(new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), true));


        return me;
    }
}
