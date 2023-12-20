package com.br.productservice.mapper;

import com.br.productservice.dto.ProductDTO;
import com.br.productservice.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductEntity dtoToEntity(ProductDTO record);

    ProductDTO entityToDto(ProductEntity entity);

    void updateFromDto(ProductDTO dto, @MappingTarget ProductEntity entity);
}