package fr.mossaab.security.dtos;

import fr.mossaab.security.models.DailyMeal;
import fr.mossaab.security.models.Reservation;
import lombok.Data;

import java.util.List;
@Data
public class UserDailyMealsDTO {
    private List<DailyMeal> dailyMeals;
    private List<Reservation> userReservations;
}
