package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.dto.ProductDto;
import java.util.List;

public interface ProductService {
    ProductDto findById(Long id);

    List<ProductDto> findAll();

    ProductDto create(ProductDto product);

    ProductDto update(Long id, ProductDto product);

    void delete(Long id);

}
