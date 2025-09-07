package com.nlksnc.api.mapper;

import com.nlksnc.api.dto.UserSignUpDto;
import com.nlksnc.api.model.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface UserSignUpMapper {
    User toEntity(UserSignUpDto userSignUpDto);
}
