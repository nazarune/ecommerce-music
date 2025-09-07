package com.nlksnc.api.mapper;

import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.model.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface UserMapper {
    UserDto toDto(User user);
}
