package fr.mossaab.security.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link fr.mossaab.security.models.DailyMeal}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyMealDto implements Serializable {
    private Long id;
    private LocalDate date;
    private List<DailyMealDishDto> dailyMealDishes;
}