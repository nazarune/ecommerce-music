package com.nlksnc.api.controller;

import com.nlksnc.api.dto.OrderDto;
import com.nlksnc.api.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable Long id){
        return orderService.findById(id);
    }

    @PostMapping
    public OrderDto create(@RequestBody OrderDto orderDto){
        return orderService.save(orderDto);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody OrderDto orderDto){
        return orderService.update(id, orderDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        orderService.delete(id);
    }
}
