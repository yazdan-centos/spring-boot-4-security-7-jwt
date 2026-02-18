package fr.mossaab.security.mappers;

import fr.mossaab.security.dtos.UserDto;
import fr.mossaab.security.entities.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)User partialUpdate(UserDto userDto, @MappingTarget User user);
}