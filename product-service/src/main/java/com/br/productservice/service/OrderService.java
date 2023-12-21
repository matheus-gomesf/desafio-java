package com.br.productservice.service;


import com.br.productservice.dto.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(UUID orderId, OrderDTO orderDTO);
    List<OrderDTO> listOrders();

    OrderDTO findOrderById(UUID orderId);

    void deleteOrders(UUID orderId);

}
