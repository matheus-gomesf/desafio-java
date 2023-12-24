package com.br.productservice.service;


import com.br.productservice.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(UUID orderId, OrderDTO orderDTO);
    Page<OrderDTO> listOrders(Pageable pageable);

    OrderDTO findOrderById(UUID orderId);

    void deleteOrders(UUID orderId);

}
