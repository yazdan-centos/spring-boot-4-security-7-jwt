package fr.mossaab.security.mappers;

import fr.mossaab.security.dtos.AppSettingDTO;
import fr.mossaab.security.models.AppSetting;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppSettingMapper {
    AppSetting toEntity(AppSettingDTO appSettingDTO);

    AppSettingDTO toDto(AppSetting appSetting);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AppSetting partialUpdate(AppSettingDTO appSettingDTO, @MappingTarget AppSetting appSetting);

}