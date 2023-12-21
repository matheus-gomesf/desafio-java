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
import java.util.*;

import static com.br.productservice.mapper.OrderMapper.ORDER_MAPPER;
import static com.br.productservice.mapper.ProductMapper.PRODUCT_MAPPER;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity toSave = ORDER_MAPPER.dtoToEntity(orderDTO);
        toSave.getProducts().clear();

        putOrderProducts(orderDTO, toSave);

        updateTotalPrice(toSave, toSave.getProducts());

        val saved = orderRepository.save(toSave);

        return ORDER_MAPPER.entityToDto(saved);
    }

    @Override
    public OrderDTO updateOrder(UUID orderId, OrderDTO orderDTO) {

        validateOrderId(orderId);

        OrderEntity orderEntity = getOrder(orderId);

        putOrderProducts(orderDTO, orderEntity);

        updateTotalPrice(orderEntity, orderEntity.getProducts());

        OrderEntity saved = orderRepository.save(orderEntity);

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

    private void putOrderProducts(OrderDTO orderDTO, OrderEntity orderEntity) {
        List<UUID> productsIds = orderDTO.getProducts().stream().map(ProductDTO::getId).toList();

        List<ProductEntity> products = productService.findAllByIds(productsIds).stream().map(PRODUCT_MAPPER::dtoToEntity).toList();
        List<ProductEntity> productsEntity = new ArrayList<>(orderEntity.getProducts());
        productsEntity.addAll(products);
        orderEntity.setProducts(productsEntity);
    }

    private void updateTotalPrice(OrderEntity orderEntity, List<ProductEntity> products) {
        Optional<BigInteger> totalPrice = products.stream().map(ProductEntity::getPrice).reduce(BigInteger::add);
        totalPrice.ifPresent(orderEntity::setTotalPrice);
    }

}
