package fr.mossaab.security.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.mossaab.security.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link fr.mossaab.security.models.CostShare}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CostSharesDto implements Serializable {
    private Long id;
    private Long personnelId;
    private Long dailyMealDishId;
    private Integer quantity = 1;
    private BigDecimal totalCost;
    private BigDecimal employeePortion;
    private BigDecimal employerPortion;
    private BigDecimal employeeSharePercentage;
    private ReservationStatus status;
}