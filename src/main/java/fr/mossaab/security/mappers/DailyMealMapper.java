package fr.mossaab.security.mappers;

import fr.mossaab.security.dtos.DailyMealDto;
import fr.mossaab.security.models.DailyMeal;

import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {DailyMealDishMapper.class})
public interface DailyMealMapper {


    DailyMeal toEntity(DailyMealDto dailyMealDto);

    @AfterMapping
    default void linkDailyMealDishes(@MappingTarget DailyMeal dailyMeal) {
        if (dailyMeal.getDailyMealDishes() != null) {
            dailyMeal.getDailyMealDishes().forEach(dailyMealDish -> dailyMealDish.setDailyMeal(dailyMeal));
        }
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DailyMealDto toDto(DailyMeal dailyMeal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DailyMeal partialUpdate(DailyMealDto dailyMealDto, @MappingTarget DailyMeal dailyMeal);
}
