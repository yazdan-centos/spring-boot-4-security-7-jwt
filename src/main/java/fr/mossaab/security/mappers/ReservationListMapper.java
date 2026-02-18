package fr.mossaab.security.mappers;

import fr.mossaab.security.dtos.ReservationListDto;
import fr.mossaab.security.models.Reservation;
import org.mapstruct.*;

/**
 * dailyMealDishDishName -> dailyMealDish.dish.name
 * dailyMealDishDailyMealMealType -> dailyMealDish.dailyMeal.mealType
 * dailyMealDishDailyMealDate -> dailyMealDish.dailyMeal.date
 * personnelName -> personnel.firstName
 * personnelLastName -> personnel.lastName
 * personnelPersCode -> personnel.persCode
 * */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationListMapper {
    @Mapping(source = "dailyMealDishDishName", target = "dailyMealDish.dish.name")
    @Mapping(source = "dailyMealDishDailyMealDate", target = "dailyMealDish.dailyMeal.date")
    @Mapping(source = "personnelLastName", target = "personnel.lastName")
    @Mapping(source = "personnelFirstName", target = "personnel.firstName")
    @Mapping(source = "personnelPersCode", target = "personnel.persCode")
    Reservation toEntity(ReservationListDto reservationListDto);

    @InheritInverseConfiguration(name = "toEntity")
    ReservationListDto toDto(Reservation reservation);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Reservation partialUpdate(ReservationListDto reservationListDto, @MappingTarget Reservation reservation);
}