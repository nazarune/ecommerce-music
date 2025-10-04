package com.nlksnc.api.service.impl;

import com.nlksnc.api.dto.ProductDto;
import com.nlksnc.api.exception.wrapper.ProductException;
import com.nlksnc.api.mapper.ProductMapper;
import com.nlksnc.api.model.Product;
import com.nlksnc.api.repository.ProductRepository;
import com.nlksnc.api.service.interfaces.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(
                () -> new ProductException("Product not found with id: " + id)
        ));
    }

    @Override
    public List<ProductDto> findAll() {
        return productMapper.toDtos(productRepository.findAll());
    }

    @Override
    @Transactional
    public ProductDto create(ProductDto product) {
        Product productEntity = productMapper.toEntity(product);
        return productMapper.toDto(productRepository.save(productEntity));
    }

    @Override
    @Transactional
    public ProductDto update(Long id, ProductDto product) {
        Product existing = productRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(product, existing, "id");
        return productMapper.toDto(productRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new ProductException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
