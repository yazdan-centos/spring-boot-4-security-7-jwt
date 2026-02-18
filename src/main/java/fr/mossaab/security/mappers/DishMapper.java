package fr.mossaab.security.mappers;

import fr.mossaab.security.dtos.DishDto;
import fr.mossaab.security.models.Dish;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DishMapper {

    Dish toEntity(DishDto dishDto);


    DishDto toDto(Dish dish);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    Dish partialUpdate(DishDto dishDto, @MappingTarget Dish dish);
}