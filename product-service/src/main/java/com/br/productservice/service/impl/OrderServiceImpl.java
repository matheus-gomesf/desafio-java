package com.br.productservice.service.impl;

import com.br.productservice.dto.OrderDTO;
import com.br.productservice.entity.OrderEntity;
import com.br.productservice.exception.ParameterNotValidException;
import com.br.productservice.exception.ResourceNotFoundException;
import com.br.productservice.repository.OrderRepository;
import com.br.productservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.br.productservice.mapper.OrderMapper.ORDER_MAPPER;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO productDTO) {
        val toSave = ORDER_MAPPER.dtoToEntity(productDTO);

        val saved = orderRepository.save(toSave);

        return ORDER_MAPPER.entityToDto(saved);
    }

    @Override
    public OrderDTO updateOrder(UUID productId, OrderDTO productDTO) {

        validateOrderId(productId);

        OrderEntity productEntity = getOrder(productId);

        ORDER_MAPPER.updateFromDto(productDTO, productEntity);
        OrderEntity saved = orderRepository.save(productEntity);

        return ORDER_MAPPER.entityToDto(saved);
    }

    @Override
    public List<OrderDTO> listOrders() {
        return orderRepository.findAll().stream().map(ORDER_MAPPER::entityToDto).toList();
    }

    @Override
    public OrderDTO findOrderById(UUID productId) {

        validateOrderId(productId);

        OrderEntity productEntity = getOrder(productId);

        return ORDER_MAPPER.entityToDto(productEntity);
    }

    @Override
    public void deleteOrders(UUID productId) {

        validateOrderId(productId);

        getOrder(productId);

        orderRepository.deleteById(productId);
    }

    private OrderEntity getOrder(UUID productId) {
        return orderRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", productId.toString()));
    }


    private void validateOrderId(UUID productId) {
        if (Objects.isNull(productId)) {
            throw new ParameterNotValidException("OrderId", "null");
        }
    }
}
