package fr.mossaab.security.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link fr.mossaab.security.models.Reservation}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonnelReservationDto implements Serializable {
    private Long id;
    private Long personnelId;
    private DailyMealDishDto1 dailyMealDish;
    private LocalDateTime createdTime = LocalDateTime.now();

    /**
     * DTO for {@link fr.mossaab.security.models.DailyMealDish}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DailyMealDishDto1 implements Serializable {
        private DailyMealDto1 dailyMeal;
        private Long dishId;

        /**
         * DTO for {@link fr.mossaab.security.models.DailyMeal}
         */
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class DailyMealDto1 implements Serializable {
            private Long id;
            private LocalDate date;
            private List<DishesDto> dailyMealDishes = new ArrayList<>();

            /**
             * DTO for {@link fr.mossaab.security.models.DailyMealDish}
             */
            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class DishesDto implements Serializable {
                private Long id;
                private Long dishId;
                private String dishName;
            }
        }
    }
}