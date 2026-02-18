package fr.mossaab.security.mappers;

import fr.mossaab.security.dtos.DailyMealDishDto;
import fr.mossaab.security.models.DailyMealDish;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DailyMealDishMapper {
    DailyMealMapper INSTANCE = Mappers.getMapper(DailyMealMapper.class);

    @Mapping(source = "dishId", target = "dish.id")
    @Mapping(source = "dailyMealId", target = "dailyMeal.id")
    DailyMealDish toEntity(DailyMealDishDto dailyMealDishDto);

    @InheritInverseConfiguration(name = "toEntity")
    DailyMealDishDto toDto(DailyMealDish dailyMealDish);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DailyMealDish partialUpdate(DailyMealDishDto dailyMealDishDto, @MappingTarget DailyMealDish dailyMealDish);
}