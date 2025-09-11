package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.dto.ProductDto;
import com.nlksnc.api.model.Product;

public interface ProductService {
    Product findById(Long id);

    Product create(ProductDto product);

    Product update(Long id, ProductDto product);

    void delete(Long id);

}
