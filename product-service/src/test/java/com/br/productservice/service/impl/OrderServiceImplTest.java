package com.br.productservice.service.impl;

import com.br.productservice.builder.OrderBuilder;
import com.br.productservice.builder.ProductBuilder;
import com.br.productservice.dto.OrderDTO;
import com.br.productservice.entity.OrderEntity;
import com.br.productservice.exception.ParameterNotValidException;
import com.br.productservice.exception.ResourceNotFoundException;
import com.br.productservice.repository.OrderRepository;
import com.br.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    private OrderDTO orderDTO;

    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        orderEntity = OrderBuilder.getOneEntity();
        orderDTO = OrderBuilder.getOne();
    }

    @Test
    void successfullyCreteOrder() {

        when(productService.findAllByIds(any())).thenReturn(Collections.singletonList(ProductBuilder.getOneDTOBebida()));
        when(orderRepository.save(any())).thenReturn(orderEntity);

        OrderDTO order = orderService.createOrder(orderDTO);

        assertNotNull(order);
        assertNotNull(order.getProducts());
        assertEquals(orderEntity.getId(), order.getId());
        assertEquals(orderEntity.getTotalPrice(), order.getTotalPrice());
    }

    @Test
    void successfullyUpdateOrder() {

        when(productService.findAllByIds(any())).thenReturn(Collections.singletonList(ProductBuilder.getOneDTOIdsSobremesa()));
        when(orderRepository.findById(any())).thenReturn(Optional.of(orderEntity));
        when(orderRepository.save(any())).thenReturn(orderEntity);

        OrderDTO order = orderService.updateOrder(orderEntity.getId(), orderDTO);

        assertNotNull(order);
        assertNotNull(order.getProducts());
        assertEquals(orderEntity.getId(), order.getId());
        assertEquals(orderEntity.getTotalPrice(), order.getTotalPrice());
        assertTrue(order.getProducts().size() > 1);
        assertTrue(orderEntity.getProducts().size() > 1);
    }

    @Test
    void errorUpdateOrderWithNullId() {

        final ParameterNotValidException e = assertThrows(ParameterNotValidException.class, () -> orderService.updateOrder(null, orderDTO));

        assertNotNull(e);
        assertEquals(e.getMessage(), "OrderId can not be 'null'");
    }

    @Test
    void errorUpdateOrderNotFound() {

        UUID orderId = UUID.randomUUID();

        final ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> orderService.updateOrder(orderId, orderDTO));

        assertNotNull(e);
        assertEquals(e.getMessage(), format("Order not found with id : '%s'", orderId));
    }

    @Test
    void successfullyListProduct() {

        when(orderRepository.findAll()).thenReturn(Collections.singletonList(orderEntity));

        List<OrderDTO> listOrders = orderService.listOrders();

        assertNotNull(listOrders);
        assertNotNull(listOrders.stream().findFirst());
        assertTrue(listOrders.stream().findFirst().isPresent());
        assertNotNull(listOrders.stream().findFirst().get());
        assertEquals(orderEntity.getId(), listOrders.stream().findFirst().get().getId());
    }

    @Test
    void successfullyFindOrderById() {

        when(orderRepository.findById(orderEntity.getId())).thenReturn(Optional.of(orderEntity));

        OrderDTO orderById = orderService.findOrderById(orderEntity.getId());

        assertNotNull(orderById);
        assertEquals(orderEntity.getId(), orderById.getId());
        assertEquals(orderEntity.getTotalPrice(), orderById.getTotalPrice());
    }

    @Test
    void errorFindOrderByIdWithNullId() {

        final ParameterNotValidException e = assertThrows(ParameterNotValidException.class, () -> orderService.findOrderById(null));

        assertNotNull(e);
        assertEquals(e.getMessage(), "OrderId can not be 'null'");
    }

    @Test
    void errorFindOrderByIdNotFound() {

        UUID orderId = UUID.randomUUID();

        final ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> orderService.findOrderById(orderId));

        assertNotNull(e);
        assertEquals(e.getMessage(), format("Order not found with id : '%s'", orderId));
    }

    @Test
    void successfullyDeleteOrder() {

        when(orderRepository.findById(orderEntity.getId())).thenReturn(Optional.of(orderEntity));

        orderService.deleteOrders(orderEntity.getId());

        verify(orderRepository, times(1)).deleteById(orderEntity.getId());
    }

    @Test
    void errorDeleteOrderWithNullId() {

        final ParameterNotValidException e = assertThrows(ParameterNotValidException.class, () -> orderService.deleteOrders(null));

        assertNotNull(e);
        assertEquals(e.getMessage(), "OrderId can not be 'null'");
    }

    @Test
    void errorDeleteOrderNotFound() {

        UUID orderId = UUID.randomUUID();

        final ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> orderService.deleteOrders(orderId));

        assertNotNull(e);
        assertEquals(e.getMessage(), format("Order not found with id : '%s'", orderId));
    }
}