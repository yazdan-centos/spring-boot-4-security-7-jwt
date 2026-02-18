package fr.mossaab.security.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.mossaab.security.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link fr.mossaab.security.models.Reservation}
 */
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class
DailyPersonnelReservationListDto implements Serializable {
    private Long id;
    private Long personnelId;
    private String personnelPersCode;
    private String personnelFirstName;
    private String personnelLastName;
    private String dailyMealDishDishName;
    private Long dailyMealDishDishId;
    private Long dailyMealId;
    private LocalDate dailyMealDate;
    private Integer dailyMealDishDishPrice;
    private CostShareDto costShares;
    private ReservationStatus reservationStatus;
}