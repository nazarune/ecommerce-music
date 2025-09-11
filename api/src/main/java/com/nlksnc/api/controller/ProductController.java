package com.nlksnc.api.controller;

import com.nlksnc.api.dto.ProductDto;
import com.nlksnc.api.mapper.ProductMapper;
import com.nlksnc.api.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable Long id) {
        return productMapper.toDto(productService.findById(id));
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productMapper.toDto(productService.create(productDto));
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id,
            @RequestBody ProductDto productDto) {
        return productMapper.toDto(productService.update(id, productDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
