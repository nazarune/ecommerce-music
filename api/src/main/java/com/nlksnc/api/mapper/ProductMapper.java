package com.nlksnc.api.mapper;

import com.nlksnc.api.dto.ProductDto;
import com.nlksnc.api.model.Product;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface ProductMapper {
    ProductDto toDto(Product product);

    List<ProductDto> toDtos(List<Product> products);

    Product toEntity(ProductDto productDto);
}
