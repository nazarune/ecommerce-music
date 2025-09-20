package com.nlksnc.api.service.impl;

import com.nlksnc.api.dto.OrderDto;
import com.nlksnc.api.exception.wrapper.OrderException;
import com.nlksnc.api.mapper.OrderMapper;
import com.nlksnc.api.model.Order;
import com.nlksnc.api.model.OrderItem;
import com.nlksnc.api.model.OrderStatus;
import com.nlksnc.api.repository.OrderRepository;
import com.nlksnc.api.service.interfaces.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Override
    public OrderDto findById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id).orElseThrow(
                () -> new OrderException("Order not found with id: " + id)
        ));
    }

    @Override
    @Transactional
    public OrderDto save(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        if (order.getOrderItems() != null) {
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.setOrder(order);
            }
        }
        order.setStatus(OrderStatus.PENDING);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderDto update(Long id, OrderDto orderDto) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new OrderException("Order not found with id: " + id);
        }
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderDto)));
    }

    @Override
    public void delete(Long id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new OrderException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}
