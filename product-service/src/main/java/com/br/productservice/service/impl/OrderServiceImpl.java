package com.br.productservice.service.impl;

import com.br.productservice.dto.OrderDTO;
import com.br.productservice.dto.ProductDTO;
import com.br.productservice.entity.OrderEntity;
import com.br.productservice.entity.ProductEntity;
import com.br.productservice.exception.ParameterNotValidException;
import com.br.productservice.exception.ResourceNotFoundException;
import com.br.productservice.repository.OrderRepository;
import com.br.productservice.service.OrderService;
import com.br.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.br.productservice.mapper.OrderMapper.ORDER_MAPPER;
import static com.br.productservice.mapper.ProductMapper.PRODUCT_MAPPER;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        val toSave = ORDER_MAPPER.dtoToEntity(orderDTO);
        toSave.getProducts().clear();

        List<UUID> productsIds = orderDTO.getProducts().stream().map(ProductDTO::getId).toList();

        List<ProductEntity> products = productService.findAllByIds(productsIds).stream().map(PRODUCT_MAPPER::dtoToEntity).toList();

        Optional<BigInteger> totalPrice = products.stream().map(ProductEntity::getPrice).reduce(BigInteger::add);

        toSave.getProducts().addAll(products);
        toSave.setTotalPrice(totalPrice.get());

        val saved = orderRepository.save(toSave);

        return ORDER_MAPPER.entityToDto(saved);
    }

    @Override
    public OrderDTO updateOrder(UUID orderId, OrderDTO orderDTO) {

        validateOrderId(orderId);

        OrderEntity productEntity = getOrder(orderId);

        ORDER_MAPPER.updateFromDto(orderDTO, productEntity);
        OrderEntity saved = orderRepository.save(productEntity);

        return ORDER_MAPPER.entityToDto(saved);
    }

    @Override
    public List<OrderDTO> listOrders() {
        return orderRepository.findAll().stream().map(ORDER_MAPPER::entityToDto).toList();
    }

    @Override
    public OrderDTO findOrderById(UUID orderId) {

        validateOrderId(orderId);

        OrderEntity productEntity = getOrder(orderId);

        return ORDER_MAPPER.entityToDto(productEntity);
    }

    @Override
    public void deleteOrders(UUID orderId) {

        validateOrderId(orderId);

        getOrder(orderId);

        orderRepository.deleteById(orderId);
    }

    private OrderEntity getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId.toString()));
    }


    private void validateOrderId(UUID orderId) {
        if (Objects.isNull(orderId)) {
            throw new ParameterNotValidException("OrderId", "null");
        }
    }
}
