package com.br.productservice.mapper;

import com.br.productservice.dto.OrderDTO;
import com.br.productservice.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OrderMapper {

    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderEntity dtoToEntity(OrderDTO record);

    OrderDTO entityToDto(OrderEntity entity);

    void updateFromDto(OrderDTO dto, @MappingTarget OrderEntity entity);
}