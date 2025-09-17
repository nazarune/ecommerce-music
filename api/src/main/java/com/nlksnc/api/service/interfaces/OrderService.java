package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.dto.OrderDto;

public interface OrderService {
    OrderDto findById(Long id);
    OrderDto save(OrderDto orderDto);
    OrderDto update(Long id, OrderDto orderDto);
    void delete(Long id);
}
