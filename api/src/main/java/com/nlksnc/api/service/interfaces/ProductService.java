package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.dto.ProductDto;

public interface ProductService {
    ProductDto findById(Long id);

    ProductDto create(ProductDto product);

    ProductDto update(Long id, ProductDto product);

    void delete(Long id);

}
