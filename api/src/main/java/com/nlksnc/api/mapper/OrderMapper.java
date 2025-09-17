package com.nlksnc.api.mapper;

import com.nlksnc.api.dto.OrderDto;
import com.nlksnc.api.model.Order;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true),
        uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);
    @Mapping(source = "userId", target = "user.id")
    Order toEntity(OrderDto orderDto);
    List<OrderDto> toDtos(List<Order> orders);
}
