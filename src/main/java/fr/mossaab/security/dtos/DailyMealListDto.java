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
public class DailyMealListDto implements Serializable {
    private Long id;
    private LocalDate date;
    private List<DailyMealDishDto1> dailyMealDishes;


    /**
     * DTO for {@link fr.mossaab.security.models.DailyMealDish}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DailyMealDishDto1 {
        private Long id;
        private DishDto1 dish;

        /**
         * DTO for {@link fr.mossaab.security.models.Dish}
         */
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class DishDto1{
            private Long id;
            private String name;
            private Integer price = 0;
        }
    }
}